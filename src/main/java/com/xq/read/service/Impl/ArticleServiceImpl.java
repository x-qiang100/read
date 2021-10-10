package com.xq.read.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xq.read.common.ServerResponse;
import com.xq.read.mapper.CommentMapper;
import com.xq.read.mapper.TagMapper;
import com.xq.read.mapper.UserMapper;
import com.xq.read.pojo.*;
import com.xq.read.mapper.ArticleMapper;
import com.xq.read.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xq.read.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {


    @Resource
    ArticleMapper articleMapper;

    @Autowired
    IUserService iUserService;

    @Autowired
    ICommentService iCommentService;

    @Autowired
    ITagService iTagService;

    @Autowired
    IRecordService iRecordService;

    @Autowired(required = false)
    UserMapper userMapper;

    @Autowired(required = false)
    CommentMapper commentMapper;

    @Autowired(required = false)
    TagMapper tagMapper;

    @Override
    public ServerResponse<ArticleVo> getArticleVoById(Integer id, Integer userId) {
        Article article = getArticleById(id);
        if(article == null){
            return ServerResponse.createByErrorMessage("没有此文章");
        }
        ArticleVo articleVo = new ArticleVo(article);
        articleVo.setCommentList(iCommentService.getCommentVosByArticleId(id) );
        articleVo.setName(iUserService.getUserById(article.getUserId()).getName() );
        articleVo.setTags(iTagService.tagNames(id ) );

        articleMapper.updateByColumn("views",id);
        final Integer bo2 = userMapper.updateBy("views", article.getUserId());

        //添加浏览记录
        if(userId != -1){
            Record record = new Record();
            record.setArticleId(id);
            record.setUserId(userId);
            iRecordService.insertRecord(record);
        }

        return ServerResponse.createBySuccess(articleVo);
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleMapper.selectById(id);
    }



    @Override
    public List<Article> getArticlesByArticleId(List<Integer> list) {
        if(list == null || list.size() == 0){
            return null;
        }
        return articleMapper.selectBatchIds(list);
    }

    @Override
    public Boolean insertArticle(Article article, List<Integer> tags) {

        int insert = articleMapper.insert(article);

        int articleId ;
        articleId = articleMapper.selectCount(new QueryWrapper<>());

        List<Tag> list = new ArrayList<>();
        for (Integer tagId : tags) {
            Tag tag = new Tag();
            tag.setArticleId(articleId);
            tag.setTagId(tagId);
            list.add(tag);
        }

        Boolean b2 = iTagService.insertTag(list);

        return (insert > 0)&&b2;
    }

    @Override
    public Boolean updateArticleLike(Integer articleId) {
        int bo1 = articleMapper.updateByColumn("likes",articleId);
        final Article article = getArticleById(articleId);
        final Integer bo2 = userMapper.updateBy("likes", article.getUserId());
        return  bo1>0 && bo2>0;
    }

    @Override
    public Boolean deleteArticle(Integer userId, Integer articleId) {

        System.out.println("articleId = " + articleId);
        System.out.println("userId = " + userId);
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("id",articleId);
        final Article article = articleMapper.selectById(articleId);

        if(article== null || !article.getUserId() .equals( userId)){
            return false;
        }
        QueryWrapper<Comment> wrapperComment = new QueryWrapper();
        QueryWrapper<Tag> wrapperTag = new QueryWrapper();
        wrapperComment.eq("articleId",articleId);
        wrapperTag.eq("articleId",articleId);
        final int c1 = commentMapper.delete(wrapperComment);

        final int t1 = tagMapper.delete(wrapperTag);

        final int i = articleMapper.deleteById(articleId);

        return i > 0 ;
    }


}
