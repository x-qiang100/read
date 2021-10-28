package com.xq.read.controller;


import com.xq.read.common.ServerResponse;
import com.xq.read.pojo.Article;
import com.xq.read.pojo.Comment;
import com.xq.read.service.ICommentService;
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
 * 评论 前端控制器
 * </p>
 *
 * @author xq
 * @since 2021-09-0
 */
@RestController
@RequestMapping("/read/comment")
public class CommentController {

    private static Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    ICommentService iCommentService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServerResponse addComment(@RequestBody Comment comment){

        comment.setUserId((Integer) SecurityUtils.getSubject().getSession().getAttribute("id"));
        try{
            Boolean b = iCommentService.insertComment(comment);
            if(b) return ServerResponse.createBySuccessMessage("添加成功");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return ServerResponse.createByErrorMessage("error");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ServerResponse delete(@RequestBody Comment comment)
    {
        //从cookie获取
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");;

        Integer commentId = comment.getId();
        try{
            Boolean b = iCommentService.deleteBy(userId, commentId);
            if(b) return ServerResponse.createBySuccessMessage("删除成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return ServerResponse.createByErrorMessage("error");
    }

}

