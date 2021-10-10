package com.xq.read.controller;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.xq.read.Config.ShiroConfig;
import com.xq.read.common.ServerResponse;
import com.xq.read.pojo.User;
import com.xq.read.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@RestController
@RequestMapping(value = "/read/user")
public class UserController {


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService iUserService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ServerResponse register(@RequestBody User user){
        String name = user.getName();
        String pwd = user.getPwd();
        if(name == null || pwd == null){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        try {
            ServerResponse register = iUserService.register(name, pwd );
            logger.info(register.getMsg());
            return register;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage() );
        }
        return ServerResponse.createByErrorMessage("注册失败");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ServerResponse login(@RequestBody User user,
                                HttpServletRequest request,
                                HttpServletResponse response){

        String  name = user.getName();
        String  password = user.getPwd();
        if(name == null || password == null){
            return ServerResponse.createByErrorMessage("参数错误");
        }

        logger.info(name+"开始登录");
        logger.info(request.getHeader("Host"));

        Subject subject = SecurityUtils.getSubject();
        String  errorMessage = "error";
        try {
//            UsernamePasswordToken token = new UsernamePasswordToken( name, password);
//            subject.login(token );
//            return ServerResponse.createBySuccess(subject.getPrincipal());
        return iUserService.login(name,password);
        }catch (Exception e){
            e.printStackTrace();
            errorMessage = e.getMessage();
            logger.error(e.getMessage());
        }

        return ServerResponse.createByErrorMessage(errorMessage);
    }


    /**
     * 修改个人信息
     * @return
     */
    @RequestMapping(value="/modify", method = RequestMethod.POST)
    public ServerResponse modify(@RequestBody User user){

        Integer userId = 1;
        String  intro = user.getIntro();
        String address = user.getAddress();
        String gender = user.getGender();
        Integer age = user.getAge();

        User userModify = new User();
        user.setId(userId);
        if (intro!= null) user.setIntro(intro);
        if (address!= null) user.setIntro(intro);
        if (gender!= null) user.setIntro(intro);
        if (age!= null) user.setIntro(intro);

        try {
            return iUserService.modify(user);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return ServerResponse.createByErrorMessage("error");
    }




    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        logger.info("hello world");
        return "<h1>hello, world</h1>";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ServerResponse<String> logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        logger.info( subject.getSession().getId().toString() );
        logger.info("再见");
        return ServerResponse.createBySuccessMessage("再见");
    }

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String error(){
        logger.info("错误");
        return "<h1>ERROR</h1>";
    }

}

