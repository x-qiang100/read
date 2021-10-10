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


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("权限配置");
        int id = (Integer) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> set = new LinkedHashSet();
        set.add("user");
        //添加角色
        authorizationInfo.setRoles(set );
        //添加权限
        authorizationInfo.setStringPermissions(set);
        return authorizationInfo;
    }

    //验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {

        String  username = (String) authenticationToken.getPrincipal();

        logger.info(username);
        logger.info("开始进行登录验证");

        String password = new String( (char[]) authenticationToken.getCredentials() );

        User user = userService.getByAccount(username);
        if(user == null){
            throw new AuthenticationException("没有此用户");
        }
        if(password.equals(user.getPwd())){
            //把当前用户存到session中
            SecurityUtils.getSubject().getSession().setAttribute("user",user);

            //传入用户名和密码，返回认证信息
            AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPwd(),"MyRealm");
            logger.info("通过验证");
            return info;
        }
        throw new AuthenticationException("密码错误");
    }
}



