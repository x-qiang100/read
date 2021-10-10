package com.xq.read.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xq.read.common.ServerResponse;
import com.xq.read.mapper.UserMapper;
import com.xq.read.pojo.Focus;
import com.xq.read.mapper.FocusMapper;
import com.xq.read.service.IFocusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xq.read.service.IUserService;
import com.xq.read.vo.UsersVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 关注信息 服务实现类
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@Service
public class FocusServiceImpl extends ServiceImpl<FocusMapper, Focus> implements IFocusService {

    @Autowired(required = false)
    FocusMapper focusMapper;

    @Autowired
    IUserService iUserService;

    @Autowired(required = false)
    UserMapper userMapper;

    @Override
    public ServerResponse<List<UsersVo>> getFansByUserId(Integer userId) {

        List<Focus> followed = getFocus(userId, "followed");

        List<Integer> list = new ArrayList<>();
        for (Focus focus : followed) {
            list.add(focus.getFollower());
        }
    System.out.println("list = " + list);
        List<UsersVo> userVos = iUserService.getUserVosByIdList(list);
    System.out.println("userVos = " + userVos);
        return ServerResponse.createBySuccess(userVos);
    }

    @Override
    public ServerResponse<List<UsersVo>> getFocusByUserId(Integer userId) {
        List<Focus> followed = getFocus(userId, "follower");
        List<Integer> list = new ArrayList<>();
        for (Focus focus : followed) {
            list.add(focus.getFollowed());
        }
        System.out.println("list = " + list);
        List<UsersVo> userVos = iUserService.getUserVosByIdList(list);
        System.out.println("userVos = " + userVos);
        return ServerResponse.createBySuccess(userVos);
    }

    @Override
    public Boolean insertFocus(Focus focus) {

        userMapper.updateBy("focus" ,focus.getFollower());
        userMapper.updateBy("fans"  ,focus.getFollowed());

        int insert = focusMapper.insert(focus);
        return insert > 0;
    }

    @Override
    public Boolean deleteFocus(Focus focus) {
        QueryWrapper<Focus> wrapper = new QueryWrapper<>();
        wrapper.eq("follower",focus.getFollower() );
        wrapper.eq("followed",focus.getFollowed() );
        final int delete = focusMapper.delete(wrapper);
        return delete > 0;
    }

    public List<Focus> getFocus(Integer userId, String  field){
        QueryWrapper<Focus> wrapper = new QueryWrapper<>();
        wrapper.eq(field,userId);
        List<Focus> foci = focusMapper.selectList(wrapper);

        return foci;
    }
}
