package com.xq.read.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xq.read.pojo.Article;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xq
 * @Date 2021/9/9 下午9:01
 * @ClassName preview
 * @Description 预览页面
 */

@Data
@NoArgsConstructor
public class PreviewVo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 作者
     */
    private String userName;

    /**
     * 标签
     */
    private List<String> tags;

    /**
     * 文章题目
     */
    private String topic;

    /**
     * 正文预览
     */
    private String preContent;

    /**
     * 创建时间
     */
    private LocalDateTime time_create;

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

    public PreviewVo(Article article) {
        this.id = article.getId();
        this.topic = article.getTopic();
        this.time_create = article.getGmt_create();
        this.views = article.getViews();
        this.likes = article.getLikes();
        this.comments = article.getComments();
        //预览100个字符
        final String content = article.getContent();
        if(content.length() > 200){
            this.preContent = content.substring(0,200);
        }else {
            this.preContent = content;
        }
    }

}

