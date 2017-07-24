package com.lz.privilegem.mapper;

import com.lz.privilegem.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> getPermissionList();

    Boolean isUsedByRole(@Param("permissionId") Integer permissionId);

    List<Permission> getResourceListByRoleId(Integer roleId);
}