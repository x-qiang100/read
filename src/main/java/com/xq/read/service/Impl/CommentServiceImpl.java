package com.xq.read.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xq.read.mapper.ArticleMapper;
import com.xq.read.pojo.Article;
import com.xq.read.pojo.Comment;
import com.xq.read.mapper.CommentMapper;
import com.xq.read.pojo.User;
import com.xq.read.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xq.read.service.IUserService;
import com.xq.read.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Resource
    CommentMapper commentMapper;

    @Autowired
    IUserService iUserService;

    @Autowired(required = false)
    ArticleMapper articleMapper;

    @Override
    public List<List<Comment>> getCommentsByArticleId(Integer articleId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("articleId", articleId);
        wrapper.eq("commentId", -1);
        List<Comment> comments = commentMapper.selectList(wrapper);
        List<List<Comment>> commentLists = new ArrayList<>();

        for (Comment comment : comments) {
            wrapper.clear();
            wrapper.eq("commentId",comment.getId());
            List<Comment> commentList = commentMapper.selectList(wrapper);
            commentList.add(0,comment);
            commentLists.add(commentList);
        }


        return commentLists;
    }

    /**
     * 加载评论姓名
     * @param articleId
     * @return
     */
    @Override
    public List<List<CommentVo>> getCommentVosByArticleId(Integer articleId) {

        List<List<Comment>> commentsByArticleId = getCommentsByArticleId(articleId);

        List<List<CommentVo>> commentVosByArticleId = new ArrayList<>();
        List<Integer> listId = new ArrayList<>();
        for (List<Comment> comments : commentsByArticleId) {
            for (Comment comment : comments) {
                listId.add(comment.getUserId());
            }
        }
        if(listId.size() == 0) return null;

        List<User> users = iUserService.getUsersByIds(listId);
        HashMap<Integer, String > map = new LinkedHashMap<>();
        for (User user : users) {
            map.put(user.getId(), user.getName());
        }
        for (List<Comment> comments : commentsByArticleId) {
            List<CommentVo> commentVos = new ArrayList<>();
            for (Comment comment : comments) {
                CommentVo commentVo = new CommentVo(comment);
                commentVo.setUserName(map.get(comment.getUserId()));
                commentVos.add(commentVo);
            }
            commentVosByArticleId.add(commentVos);
        }

        return commentVosByArticleId;
    }


    @Override
    public Boolean insertComment(Comment comment) {
        int insert = commentMapper.insert(comment);
        articleMapper.updateByColumn("comments",comment.getArticleId());
        return insert > 0;
    }

    @Override
    public Boolean deleteBy(Integer userId, Integer commentId) {
        System.out.println("commentId = " + commentId);
        System.out.println("userId = " + userId);
        final Comment comment = commentMapper.selectById(commentId);

        Integer delete = 0;
        if(comment == null) return false;
        final Article article = articleMapper.selectById(comment.getArticleId() );
        if(article == null) return false;
        if(comment.getUserId().equals(userId) || article.getUserId().equals(userId) ){
            delete = commentMapper.deleteById(commentId );
        }else {
            return false;
        }
        return delete > 0;
    }


}
