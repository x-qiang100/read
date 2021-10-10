package com.xq.read.service;

import com.xq.read.common.ServerResponse;
import com.xq.read.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xq.read.vo.UsersVo;

import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
public interface IUserService extends IService<User> {

    /**
     * 注册
     * @param name
     * @param password
     * @return
     */
    ServerResponse register(String name, String password);


    /**
     * 登录
     * @param name
     * @param password
     * @return
     */
    ServerResponse login(String name, String password);

    /**
     * 修改个人信息
     * @param user
     * @return
     */
    ServerResponse<User> modify(User user);

    /**通过id获取user信息，user集合
     *
     * @param ids
     * @return
     */
    List<User> getUsersByIds(List<Integer> ids);

    /**通过id获取user信息
     *
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 通过id集合获得UsersVo视图
     * @param ids
     * @return
     */
    List<UsersVo> getUserVosByIdList(List<Integer> ids);


    /**
     * 通过账户名获的user用户
     * @param name
     * @return
     */
    User getByAccount(String name);


}
