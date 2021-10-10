package com.xq.read.service;

import com.xq.read.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xq.read.vo.CommentVo;

import java.util.List;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
public interface ICommentService extends IService<Comment> {

    /**根据文章id获取评论消息
     *
     * @param articleId
     * @return
     */
    List<List<Comment>> getCommentsByArticleId(Integer articleId);

    /**获取某文章评论视图
     *
     * @param articleId
     * @return
     */
    List<List<CommentVo>> getCommentVosByArticleId(Integer articleId);

    /**
     * 插入一条评论
     * @param comment
     * @return
     */
    Boolean insertComment(Comment comment);

    /**
     * 删除一条评论
     * 只有评论用户 或 文章用户可以删除
     * @param userId
     * @param commentId
     * @return
     */
    Boolean deleteBy(Integer userId, Integer commentId);

}
