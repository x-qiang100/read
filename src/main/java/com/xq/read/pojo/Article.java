package com.xq.read.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class Article implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 作者
     */
      @TableField("userId")
    private Integer userId;

      /**
     * 文章题目
     */
      private String topic;

      /**
     * 正文
     */
      private String content;

      /**
     * 创建时间
     */
      private LocalDateTime gmt_create;

      /**
     * 修改时间
     */
      private LocalDateTime gmt_update;

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


}
