package com.lz.privilegem.biz.impl;

import com.lz.privilegem.biz.Interface.IPermissionService;
import com.lz.privilegem.entity.Permission;
import com.lz.privilegem.entity.User;
import com.lz.privilegem.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lizhi on 2017/7/7.
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    private void setPermissionInsert(Permission Permission,User operator)
    {
        Date d = new Date();
        Permission.setCreatePerson(operator.getUsername());
        Permission.setUpdatePerson(operator.getUsername());
        Permission.setCreateDate(d);
        Permission.setUpdateDate(d);
    }
    private void setPermissionUpdate(Permission Permission,User operator)
    {
        Date d = new Date();
        Permission.setUpdatePerson(operator.getUsername());
        Permission.setUpdateDate(d);
    }

    public List<Permission> getPermissionList() {
        return permissionMapper.getPermissionList();
    }

    public Permission getPermissionById(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    public Integer createPermission(Permission permission, User user) {
        setPermissionInsert(permission,user);
        return permissionMapper.insertSelective(permission);
    }

    public Integer updatePermissionById(Permission permission, User user) {
        setPermissionUpdate(permission,user);
        return permissionMapper.updateByPrimaryKeySelective(permission);
    }

    public Integer deletePermissionById(Integer id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    public Boolean isUsedByRole(Integer permissionId) {
        return permissionMapper.isUsedByRole(permissionId);
    }

    public List<Permission> getPermissionListByRoleId(Integer roleId) {
        return permissionMapper.getResourceListByRoleId(roleId);
    }

    public String getPermissionTree(Integer roleId) {
        Set<Integer> setResource = new HashSet<Integer>();

        if (roleId != null) {
            List<Permission> tempResourceList = permissionMapper.getResourceListByRoleId(roleId);
            if (tempResourceList != null && tempResourceList.size() > 0) {
                for (Permission r : tempResourceList) {
                    setResource.add(r.getId());
                }
            }
        }

        List<Permission> resourceList = permissionMapper.getPermissionList();
        Set<Integer> setParent = new HashSet<Integer>();
        for (Permission r : resourceList) {
            setParent.add(r.getParentId());
        }

        StringBuilder sb = new StringBuilder();
        Map<String, Integer> mapModule = new HashMap<String, Integer>();
        sb.append("[");
        int i = 0;

        if (i != 0) {
            sb.append(",");
        }
        i++;
        sb.append("{")
                .append(",\"parent\":\"").append("#\"");

        //前两个级别默认打开
        sb.append(",\"state\":{");
        sb.append("\"opened\":true");
        sb.append("}");
        sb.append("}");
        i = 0;
        for (Permission r : resourceList) {
            sb.append(",");
            i++;
            sb.append("{")
                    .append("\"id\":\"").append(r.getId()).append("\"")

                    .append(",\"text\":\"").append(r.getName()).append("\"")

                    .append("\",\"sortNo\":").append(r.getSortNo()).append("}");
            if (setResource.contains(r.getId())) {
                sb.append(",\"state\":{\"opened\":true}");
            }
            //最后一个级别换个绿色图标
            if (!setParent.contains(r.getId())) {
                sb.append(", \"icon\": \"fa fa-briefcase icon-success\"");
            }
            sb.append("}");
        }
        sb.append("]");
        return sb.toString();
    }
}
