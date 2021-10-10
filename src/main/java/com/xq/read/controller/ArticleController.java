package com.xq.read.controller;


import com.xq.read.common.ServerResponse;
import com.xq.read.pojo.Article;
import com.xq.read.service.IArticleService;
import com.xq.read.service.IDict_tagService;
import com.xq.read.vo.ArticleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@RestController
@RequestMapping("/read/article")
public class ArticleController {

    private  static Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    IArticleService iArticleService;

    @Autowired
    IDict_tagService iDict_tagService;

    /**
     * 若id为-1则为游客浏览
     * @param article
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ServerResponse<ArticleVo> getArticle(Integer article){
        //若是游客登陆，则-1
        Integer userId = 1;
        try{
            ServerResponse<ArticleVo> articleVoById = iArticleService.getArticleVoById(article, userId);
            logger.info("获取文章"+articleVoById.getData().getId());
            return articleVoById;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return ServerResponse.createByErrorMessage("error");
    }

    /**
     * 添加文章
     *
     * @param articleVo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServerResponse addArticle(@RequestBody ArticleVo articleVo)
    {
        //通过token或cookie获取
        Integer userId = 4;

        Article article = new Article();
        article.setUserId(userId);
        article.setTopic(articleVo.getTopic());
        article.setContent(articleVo.getContent());

        List<Integer> tags = iDict_tagService.getIdByName(articleVo.getTags() );
        try{
            Boolean b = iArticleService.insertArticle(article, tags);
            if(b) return ServerResponse.createBySuccessMessage("添加成功");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return ServerResponse.createByErrorMessage("error");
    }

    @RequestMapping(value = "/addLike", method = RequestMethod.GET)
    public ServerResponse addArticle(Integer article)
    {
        try{
            Boolean b = iArticleService.updateArticleLike(article);
            if(b) return ServerResponse.createBySuccessMessage("添加成功");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return ServerResponse.createByErrorMessage("error");
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ServerResponse delete(@RequestBody Article article)
    {
        //从cookie获取
        Integer userId = 3;
        Integer articleId = article.getId();
        try{
            Boolean b = iArticleService.deleteArticle(userId, articleId);
            if(b) return ServerResponse.createBySuccessMessage("删除成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return ServerResponse.createByErrorMessage("error");
    }

}

