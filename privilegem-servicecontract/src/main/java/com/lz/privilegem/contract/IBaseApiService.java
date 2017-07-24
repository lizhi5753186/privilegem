package com.lz.privilegem.contract;

import com.lz.privilegem.entity.User;

/**
 * Created by lizhi on 2017/7/7.
 */
public interface IBaseApiService {
    User getUserByUsername(String username);
    Integer updateUserById(User user);
}
