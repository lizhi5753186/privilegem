package com.lz.privilegem.mapper;

import com.lz.privilegem.entity.Role;
import com.lz.privilegem.entity.RoleSearch;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    Integer deleteRoleById(Integer id);

    Boolean isUsedByUser(Integer roleId);

    Integer getRoleTotalBySearch(RoleSearch searchModel);

    List<Role> getRoleListBySearch(RoleSearch searchModel, RowBounds rowBounds);

    List<Role> getRoleListByUserId(@Param("userId")Integer userId);

    List<Role> getRoleList();

    void assignPermissions(@Param("permissionIds")List<Integer> permissionIds,@Param("roleId")Integer roleId);
}