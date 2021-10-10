package com.xq.read.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xq.read.common.ServerResponse;
import com.xq.read.pojo.User;
import com.xq.read.mapper.UserMapper;
import com.xq.read.service.IFocusService;
import com.xq.read.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xq.read.vo.UsersVo;
import org.apache.kafka.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    IFocusService iFocusService;

    @Override
    public ServerResponse register(String name, String password) {
        User data = findByName(name);
        if(data != null) {
            return ServerResponse.createByErrorMessage("昵称已存在");
        }
        User user = new User();
        user.setName(name );
        user.setPwd(password );
        userMapper.insert(user );
        return ServerResponse.createBySuccess("添加成功");
    }

    @Override
    public ServerResponse login(String name, String password) {

        Subject subject;

        User user = findByName(name);
        if(user == null){
            return ServerResponse.createByErrorMessage("没有此用户");
        }
//        if(!user.getPwd().equals(password)){
//            return ServerResponse.createByErrorMessage("密码错误");
//        }
        return ServerResponse.createBySuccess(user );
    }

    @Override
    public ServerResponse<User> modify(User user) {
        user.setPwd(null);
        userMapper.updateById(user);
        User newUser = userMapper.selectById(user.getId());
        return ServerResponse.createBySuccess(newUser);
    }

    @Override
    public List<User> getUsersByIds(List<Integer> ids) {

        if(ids == null || ids.size() == 0){
            return null;
        }
        return userMapper.selectBatchIds(ids);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<UsersVo> getUserVosByIdList(List<Integer> ids) {
        List<User> users = getUsersByIds(ids);
        List<UsersVo> usersVoList = new ArrayList<>();
        for (User user : users) {
            UsersVo usersVo = new UsersVo();
            usersVo.setId(user.getId() );
            usersVo.setName(user.getName() );
            usersVo.setIntro(user.getIntro() );
            usersVoList.add(usersVo );
        }
        return usersVoList;
    }

    @Override
    public User getByAccount(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        return userMapper.selectOne(wrapper);
    }


    private User findByName(String name){

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        List<User> list = userMapper.selectList(wrapper);
        if(list.size() == 0) return null;
        return list.get(0);
    }

    public User findById(int id){
        return userMapper.selectById(id);
    }

}
