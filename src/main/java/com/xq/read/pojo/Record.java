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
 * 浏览记录
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class Record implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 浏览用户
     */
      @TableField("userId")
    private Integer userId;

      /**
     * 浏览文章
     */
      @TableField("articleId")
    private Integer articleId;

      /**
     * 浏览时间
     */
      private LocalDateTime time;


}
