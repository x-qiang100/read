package com.xq.read.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xq.read.pojo.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author xq
 * @Date 2021/9/9 下午10:28
 * @ClassName CommentVo
 * @Description 评论
 */
@Data
public class CommentVo {


    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 评论发布时间
     */
    private LocalDateTime gmt_time;

    /**
     * 评论内容
     */
    private String  content;

    public CommentVo(Comment comment){
        this.id = comment.getId();
        this.userId = comment.getUserId();
        this.content = comment.getContent();
        this.gmt_time = comment.getGmt_time();
    }

}

