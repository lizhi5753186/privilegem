package com.lz.privilegem.mapper;

import com.lz.privilegem.entity.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}