package com.xq.read.mapper;

import com.xq.read.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author xq
 * @since 2021-09-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    Integer updateBy(String  column, Integer userId);

}
