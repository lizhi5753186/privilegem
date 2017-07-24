package com.lz.privilegem.mapper;

import com.lz.privilegem.entity.RolePermission;

public interface RolePermissionMapper {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);
}