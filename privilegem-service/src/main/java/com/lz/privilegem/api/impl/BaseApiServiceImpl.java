package com.lz.privilegem.api.impl;

import com.lz.privilegem.contract.IBaseApiService;
import org.springframework.beans.factory.annotation.Autowired;

import com.lz.privilegem.mapper.UserMapper;
import com.lz.privilegem.entity.User;

/**
 * Created by lizhi on 2017/7/7.
 */
public class BaseApiServiceImpl implements IBaseApiService {

    @Autowired
    private UserMapper userMapper;

    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    public Integer updateUserById(User user)
    {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
