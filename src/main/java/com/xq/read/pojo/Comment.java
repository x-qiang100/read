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
 * 评论
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class Comment implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 用户id
     */
      @TableField("userId")
    private Integer userId;

      /**
     * 文章id
     */
      @TableField("articleId")
    private Integer articleId;

      /**
     * 评论的id，-1为一级评论，其余为二级评论
     */
      @TableField("commentId")
    private Integer commentId;

      /**
     * 评论发布时间
     */
      private LocalDateTime gmt_time;

      /**
     * 评论内容
     */
      private String content;


}
