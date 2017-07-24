package com.lz.privilegem.biz.Interface;

import com.lz.privilegem.entity.Permission;
import com.lz.privilegem.entity.User;

import java.util.List;

/**
 * Created by lizhi on 2017/7/7.
 */
public interface IPermissionService {

    List<Permission> getPermissionList();

    /**
     * 根据id获取Permission对象
     * @param id
     * @return
     */
    Permission getPermissionById(Integer id);

    /**
     * 新增记录
     * @param Permission
     * @param user
     * @return
     */
    Integer createPermission(Permission Permission,User user);

    /**
     * 根据id修改一条记录
     * @param Permission
     * @param user
     * @return
     */
    Integer updatePermissionById(Permission Permission,User user);

    /**
     * 根据id删除一条Permission
     * @param id
     * @return
     */
    Integer deletePermissionById(Integer id);

    /**
     * 根据资源id判断是否被角色使用
     * @param PermissionId
     * @return
     */
    Boolean isUsedByRole(Integer PermissionId);

    /**
     * 给定角色具有权限的资源列表
     * @param roleId
     * @return
     */
    List<Permission> getPermissionListByRoleId(Integer roleId);

    /**
     * 获取菜单资源树
     * @param roleId 如果传入roleId，则对此roleId用户的相关菜单资源选中
     * @return
     */
    String getPermissionTree(Integer roleId);
}
