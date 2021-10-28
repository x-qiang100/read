package com.xq.read.controller;


import com.xq.read.common.ServerResponse;
import com.xq.read.pojo.Article;
import com.xq.read.pojo.Collection;
import com.xq.read.service.IArticleService;
import com.xq.read.service.ICollectionService;
import com.xq.read.service.IPreviewService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 收藏夹 前端控制器
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@RestController
@RequestMapping("/read/collection")
public class CollectionController {

    @Autowired
    ICollectionService iCollectionService;

    @Autowired
    IPreviewService iPreviewService;

    private static Logger logger = LoggerFactory.getLogger(CollectionController.class);

    /**
     * 获得某用户的收藏夹
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ServerResponse getCollection( @RequestParam(required = false, defaultValue = "1")Integer page) {
        //修改
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");;

        try{
            return iPreviewService.getCollectArticles(userId, page);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return ServerResponse.createByErrorMessage("error");
    }

    /**
     *添加收藏记录
     *
     * @param article
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServerResponse addCollect( @RequestBody Article article){
        //
        if(article.getId() == null){
            return ServerResponse.createByErrorMessage("error");
        }
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");;
        Collection collection = new Collection();
        collection.setUserId(userId);
        collection.setArticleId(article.getId() );
        try{
            Boolean bool = iCollectionService.insertCollect(collection);
            if(bool) return ServerResponse.createBySuccessMessage("添加成功");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return ServerResponse.createByErrorMessage("error");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ServerResponse delete(@RequestBody Collection collection)
    {
        //从cookie获取
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");;
        collection.setUserId(userId );

        try{
            Boolean b = iCollectionService.deleteCollect(collection);
            if(b) return ServerResponse.createBySuccessMessage("删除成功");
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return ServerResponse.createByErrorMessage("error");
    }
}

