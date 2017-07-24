package com.lz.privilegem.biz.Interface;

import com.lz.privilegem.entity.Role;
import com.lz.privilegem.entity.RoleSearch;
import com.lz.privilegem.entity.User;

import java.util.List;

/**
 * Created by lizhi on 2017/7/7.
 */
public interface IRoleService {

    /**
     * 根据id获取Role对象
     * @param id
     * @return
     */
    Role getRoleById(Integer id);

    /**
     * 新增记录
     * @param role
     * @param user
     * @return
     */
    Integer createRole(Role role,User user);

    /**
     * 根据id修改一条记录
     * @param role
     * @return
     */
    Integer updateRoleById(Role role,User user);

    /**
     * 根据id删除角色
     * @param id
     * @return
     */
    Integer deleteRoleById(Integer id);

    /**
     * 根据条件查询角色列表
     * @return
     */
    List<Role> getRoleListBySearch(RoleSearch search);

    Integer getRoleTotalBySearch(RoleSearch searchModel);

    List<Role> getRoleListByUserId(Integer userId);

    /**
     * 角色是否被用户使用
     * @param roleId
     * @return
     */
    Boolean isUsedByUser(Integer roleId);

    /**
     * 所有角色列表
     * @return
     */
    List<Role> getRoleList();

    /**
     * 根据查询条件，返回DataTables控件需要的Json数据格式
     * @param searchModel
     * @return
     */
    String getRoleDataTables(RoleSearch searchModel);

    /**
     * 返回DataTables控件需要的一行Json数据格式
     * @param id
     * @return
     */
    String getRoleDataRow(Integer id);

    /**
     * 返回jquery-multi-select需要的options数据
     * @param userId
     * @return
     */
    String getRoleForOptions(Integer userId);

    /**
     * 根据选择的字符串(,分割)，对角色赋予权限
     * @param roleId
     * @param checkedStr
     */
    String assignPermission(Integer roleId,String checkedStr);
}
