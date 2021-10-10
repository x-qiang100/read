package com.xq.read.vo;

import com.xq.read.pojo.Article;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xq
 * @Date 2021/9/9 下午9:19
 * @ClassName Content
 * @Description 一个文章内容
 */
@Data
@NoArgsConstructor
public class ArticleVo {


    /**
     * 主键
     */
    private Integer id;
    /**
     * 作者id
     */
    private Integer userId;

    /**
     * 作者
     */
    private String  name;

    /**
     * 文章题目
     */
    private String topic;

    /**
     * 文章标签
     */
    private List<String> tags;

    /**
     * 正文
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime gmt_create;

    /**
     * 浏览量
     */
    private Integer views;

    /**
     * 点赞量
     */
    private Integer likes;

    /**
     * 评论量
     */
    private Integer comments;

    /**
     * 收藏量
     */
    private Integer collect;

    /**
     * 评论
     */
    private List<List<CommentVo>> commentList;

    public ArticleVo(Article article) {
        this.id = article.getId();
        this.userId = article.getUserId();
        this.topic = article.getTopic();
        this.content = article.getContent();
        this.gmt_create = article.getGmt_create();
        this.views = article.getViews();
        this.likes = article.getLikes();
        this.comments = article.getComments();
        this.collect = article.getCollect();
    }

}

