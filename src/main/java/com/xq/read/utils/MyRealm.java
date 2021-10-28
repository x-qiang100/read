package com.xq.read.utils;

import com.xq.read.pojo.User;
import com.xq.read.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author xq
 * @Date 2021/9/8 下午8:00
 * @ClassName MyRealm
 * @Description 我的Realm
 */

public class MyRealm extends AuthorizingRealm {

    @Autowired
    IUserService userService;

    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);


    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("权限配置");
        int id = (Integer) principalCollection.getPrimaryPrincipal();
        //权限信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //添加角色
        Set<String> roles = new LinkedHashSet<>();
        roles.add("user");
        authorizationInfo.setRoles(roles );
        //添加权限
        Set<String> permission = new LinkedHashSet<>();
        permission.add("general");
        authorizationInfo.setStringPermissions(permission);
        return authorizationInfo;
    }

    /**
     * 验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {

        if(authenticationToken.getPrincipal() == null){
            return null;
        }
        String  name = (String) authenticationToken.getPrincipal();

        logger.info(name+"开始身份验证");
        String password = new String( (char[]) authenticationToken.getCredentials() );
        System.out.println("authenticationToken.getPrincipal() = " + authenticationToken.getPrincipal());
        System.out.println("authenticationToken.getCredentials() = " + authenticationToken.getCredentials());
        System.out.println("password = " + password);

        User user = userService.getByAccount(name);
        System.out.println("user.getPwd() = " + user.getPwd());
        if(password.equals(user.getPwd())){
            //把当前用户存到session中
            SecurityUtils.getSubject().getSession().setAttribute("id",user.getId());
            SecurityUtils.getSubject().getSession().setAttribute("name",name);
            //传入用户名和密码，返回认证信息
            AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPwd(),"MyRealm");
            logger.info("通过验证");
            return info;
        }
        throw new AuthenticationException("密码错误");
    }
}



