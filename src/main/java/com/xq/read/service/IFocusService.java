package com.xq.read.service;

import com.xq.read.common.ServerResponse;
import com.xq.read.pojo.Focus;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xq.read.vo.UsersVo;

import java.util.List;

/**
 * <p>
 * 关注信息 服务类
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
public interface IFocusService extends IService<Focus> {

    /**
     * 通过id获取用户粉丝，
     * 关注该用户的用户id集合
     * @param userId
     * @return
     */
    ServerResponse<List<UsersVo>> getFansByUserId(Integer userId);

    /**
     * 通过id获取用户关注列表
     * 该用户关注的用户id集合
     * @param userId
     * @return
     */
    ServerResponse<List<UsersVo>> getFocusByUserId(Integer userId);

    /**
     * 插入一条关注记录
     * @param focus
     * @return
     */
    Boolean insertFocus(Focus focus);

    Boolean deleteFocus(Focus focus);

}
