package com.xq.read.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 昵称
     */
      private String name;

      /**
     * 性别
     */
      private String gender;

      /**
     * 年龄
     */
      private Integer age;

      /**
     * 个人简介
     */
      private String intro;

      /**
     * 地址
     */
      private String address;

      /**
     * 图片链接
     */
      private String img;

      /**
     * 手机号
     */
      private String phone;

      /**
     * 密码
     */

      @JsonIgnore
      private String pwd;

      /**
     * 被点赞量
     */
      private Integer likes;

      /**
     * 浏览量
     */
      private Integer views;

      /**
     * 文章数量
     */
      private Integer articles;

      /**
     * 粉丝(被关注数量)
     */
      private Integer fans;

      /**
     * 关注数量
     */
      private Integer focus;


}
