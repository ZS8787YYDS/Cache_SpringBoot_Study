package com.zisheng.Service.Impl;

import com.zisheng.Entity.User;
import com.zisheng.Mapper.UserMapper;
import com.zisheng.Service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ServiceImpl extends com.baomidou.mybatisplus.extension.service.impl.ServiceImpl<UserMapper,User> implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 插入功能
     * @param user
     */
    @Override
    public void insertUser(User user) {
        userMapper.insert(user);
    }

    /**
     * 修改功能
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }
}
