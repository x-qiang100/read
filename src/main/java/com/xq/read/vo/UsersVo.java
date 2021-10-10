package com.xq.read.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author xq
 * @Date 2021/9/9 下午9:30
 * @ClassName UsersVo
 * @Description 用户预览
 */

@Data
public class UsersVo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 昵称
     */
    private String name;

    /**
     * 个人简介
     */
    private String intro;

}

