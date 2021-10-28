package com.xq.read.Config;

import com.xq.read.utils.DefaultHeaderSessionManager;
import com.xq.read.utils.MyRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xq
 * @Date 2021/9/8 下午8:16
 * @ClassName ShiroConfig
 * @Description
 */


@Configuration
public class ShiroConfig {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean
    public MyRealm myAuthRealm(){
        logger.info("进入MyReal");
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }

    @Bean
    public SessionManager sessionManager(){
        logger.info("进入会话管理");
        DefaultHeaderSessionManager sessionManager = new DefaultHeaderSessionManager();
        return sessionManager;
    }


    @Bean
    public DefaultWebSecurityManager securityManager(MyRealm myRealm){
        logger.info("DefaultWebSecurityManager");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(myRealm );
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        logger.info("Shiro filter");
        //定义
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置自定义的securityManger
        shiroFilterFactoryBean.setSecurityManager(securityManager );
//        //设置默认登录url
//        shiroFilterFactoryBean.setLoginUrl("/read/user/login");
//        //设置成功之后跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("/read/user/homepage");
//        //未授权界面
//        shiroFilterFactoryBean.setUnauthorizedUrl("/read/user/403");
//
        Map<String ,String> map = new LinkedHashMap<>();
//
//        //不需要权限——anon
//        map.put("/read/user/hello","anon");
//        map.put("/read/user/login","anon");
//        map.put("/read/user/register","anon");
//
//        //需要权限——authc
//        map.put("/read/user/index","authc");
//        map.put("/read/**","authc");

//        map.put("/read/user/logout","logout");

        map.put("/read/**","anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map );
        return shiroFilterFactoryBean;
    }

}

