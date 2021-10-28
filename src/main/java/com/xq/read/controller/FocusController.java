package com.xq.read.controller;


import com.xq.read.common.ServerResponse;
import com.xq.read.pojo.Article;
import com.xq.read.pojo.Focus;
import com.xq.read.service.IFocusService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 关注信息 前端控制器
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@RestController
@RequestMapping("/read/focus")
public class FocusController {

    private static Logger logger = LoggerFactory.getLogger(FocusController.class);

    @Autowired
    IFocusService iFocusService;

    /**
     *  添加关注记录
     * @param focus
     * @return
     */
    @RequestMapping(value = "/addFocus", method = RequestMethod.POST)
    public ServerResponse addFocus(@RequestBody Focus focus){
        //
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");;

        Integer follower = userId;
        Integer followed = focus.getFollowed();
        focus.setFollower(follower);
        focus.setFollowed(followed);
        try{
            Boolean b = iFocusService.insertFocus(focus);
            if(b) return ServerResponse.createBySuccessMessage("添加成功");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return ServerResponse.createByErrorMessage("error");
    }

    /**
     * 关注我的人
     * @param
     * @return
     */
    @RequestMapping(value = "/getFans", method = RequestMethod.GET)
    public ServerResponse getFans(){
        //
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");;
        try{
            return iFocusService.getFansByUserId(userId);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return ServerResponse.createByErrorMessage("error");
    }

    /**
     * 我关注的人
     * @return
     */
    @RequestMapping(value = "/getFocus", method = RequestMethod.GET)
    public ServerResponse getFocus(){
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");;
        try{
            return iFocusService.getFocusByUserId(userId);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return ServerResponse.createByErrorMessage("error");
    }

    /**
     *
     * @param focus
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ServerResponse delete(@RequestBody Focus focus)
    {
        //从cookie获取
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");;
        focus.setFollower(userId);

        try{
            Boolean b = iFocusService.deleteFocus(focus);
            if(b) return ServerResponse.createBySuccessMessage("删除成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return ServerResponse.createByErrorMessage("error");
    }

}

