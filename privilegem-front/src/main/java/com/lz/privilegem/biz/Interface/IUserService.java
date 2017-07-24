package com.lz.privilegem.biz.Interface;

import com.lz.privilegem.entity.User;
import com.lz.privilegem.entity.UserSearch;

import java.util.List;

/**
 * Created by lizhi on 2017/7/7.
 */
public interface IUserService {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User getUserById(Integer id);

    Integer getUserTotalBySearch(UserSearch search);

    /**
     * 根据条件查询用户List
     * @param search
     * @return
     */
    List<User> getUserListBySearch(UserSearch search);

    /**
     * 新增用户
     * @param user
     * @param operator
     * @return
     */
    Integer createUser(User user,User operator);

    /**
     * 根据id更新用户
     * @param user
     * @param operator
     * @return
     */
    Integer updateUserById(User user,User operator);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    Integer deleteUserById(Integer id,User operator);

    /**
     * 用户赋予角色
     * @param id
     * @param selectedStr
     * @return
     */
    String assignRole(Integer id,String selectedStr);

    String getUserDataTables(UserSearch searchModel);

    String getUserDataRow(Integer id);

    boolean checkPassword(String username, String password);

}
