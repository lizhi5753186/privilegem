package com.lz.privilegem.mapper;

import com.lz.privilegem.entity.UserSearch;
import org.apache.ibatis.annotations.Param;
import com.lz.privilegem.entity.User;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getUserByUsername(@Param("username")String username);

    Integer deleteUserRoleById(Integer id);

    void assignRoles(@Param("roleIds")List<Integer> roleIds,@Param("userId")Integer userId);

    Integer getUserTotalBySearch(UserSearch searchModel);

    List<User> getUserListBySearch(UserSearch searchModel, RowBounds rowBounds);
}