package com.xq.read.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xq.read.common.ServerResponse;
import com.xq.read.common.constant;
import com.xq.read.mapper.ArticleMapper;
import com.xq.read.pojo.Article;
import com.xq.read.pojo.User;
import com.xq.read.service.*;
import com.xq.read.vo.PreviewVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xq
 * @Date 2021/9/9 下午9:34
 * @ClassName PreviewServiceImpl
 * @Description
 */
@Service
public class PreviewServiceImpl implements IPreviewService {

    @Resource
    ArticleMapper articleMapper;

    @Autowired
    IUserService iUserService;

    @Autowired
    IArticleService iArticleService;

    @Autowired
    ITagService iTagService;

    @Autowired
    ICollectionService iCollectionService;


    @Override
    public ServerResponse getIndex(Integer page) {
        Integer index = (page-1)* constant.pageSize ;

        List<Article> articles = articleMapper.selectListPageOrderByLike(index, constant.pageSize);
        Integer sum = articleMapper.selectCount(null);
        System.out.println("index = " + index);
        for (Article article : articles) {
            System.out.println("article.getTopic() = " + article.getTopic());
        }
        //总页数
        int pages = (sum-1)/constant.pageSize + 1;
        Map map = new LinkedHashMap();
        map.put("preview",getVoByArticle(articles));
        map.put("pages",pages);
        return ServerResponse.createBySuccess(map );
    }

    @Override
    public ServerResponse getArticlesByTags(List<Integer> tagIds, Integer page) {
        return null;
    }

    @Override
    public ServerResponse getArticlesByUserId(Integer userId, Integer page) {

        User user = iUserService.getUserById(userId);
        Integer index = (page-1)* constant.pageSize;
        List<Article> articles = articleMapper.selectListPageByUserId(index, constant.pageSize, userId);
        List<PreviewVo> list = new ArrayList<>();
        System.out.println("index = " + index);
        for (Article article : articles) {
            System.out.println("article.getTopic() = " + article.getTopic());
        }
        for (Article article : articles) {
            PreviewVo previewVo = new PreviewVo(article);
            previewVo.setUserName(user.getName());
            previewVo.setTags(iTagService.tagNames(article.getId() ));
            list.add(previewVo);
        }
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",userId);
        Integer sum = articleMapper.selectCount(wrapper);
        //总页数
        int pages = (sum-1)/constant.pageSize + 1;
        Map map = new LinkedHashMap();
        map.put("preview",getVoByArticle(articles));
        map.put("pages",pages);
        return ServerResponse.createBySuccess(map );    }

    @Override
    public ServerResponse getArticlesByKeyWord(String keyword, Integer page) {
        Integer index = (page-1)* constant.pageSize ;
        keyword = "%" + keyword + "%";

        List<Article> articles = articleMapper.selectListPageByKeyword(index, constant.pageSize,keyword);
        for (Article article : articles) {
            System.out.println("article.getTopic() = " + article.getTopic());
        }
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.like("topic" ,keyword);
        Integer sum = articleMapper.selectCount(wrapper);
        //总页数
        int pages = (sum-1)/constant.pageSize + 1;
        Map map = new LinkedHashMap();
        map.put("preview",getVoByArticle(articles));
        map.put("pages",pages);
        return ServerResponse.createBySuccess(map );
    }

    @Override
    public ServerResponse getCollectArticles(Integer userId, Integer page) {

        List<Integer> articleIds = iCollectionService.getArticles(userId);
        int sum = articleIds.size();
        List<Integer> articleIdsCur = new ArrayList<>();
        int index = (page - 1)*constant.pageSize;
        for (int integer = 0; integer < constant.pageSize; integer++) {
                if(index + integer  >= sum){
                    break;
                }
                articleIdsCur.add(index+integer);
        }

        List<Article> articles = iArticleService.getArticlesByArticleId(articleIdsCur);

        int pages = (sum-1)/constant.pageSize + 1;
        Map map = new LinkedHashMap();
        map.put("preview",getVoByArticle(articles));
        map.put("pages",pages);
        return ServerResponse.createBySuccess(map );
    }

    List<PreviewVo> getVoByArticle(List<Article> articles){

        List<PreviewVo> list = new ArrayList<>();
        for (Article article : articles) {
            PreviewVo previewVo = new PreviewVo(article);
            System.out.println(article.getId());
            previewVo.setUserName(iUserService.getUserById(article.getUserId() ).getName() );
            previewVo.setTags(iTagService.tagNames(article.getId() ));
            list.add(previewVo);
        }
        return list;
    }




}

