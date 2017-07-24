package com.lz.privilegem.biz.impl;

import com.lz.privilegem.biz.Interface.IRoleService;
import com.lz.privilegem.entity.Role;
import com.lz.privilegem.entity.RoleSearch;
import com.lz.privilegem.entity.User;
import com.lz.privilegem.mapper.RoleMapper;
import com.lz.privilegem.utils.ConstantUtil;
import com.lz.privilegem.utils.StringUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lizhi on 2017/7/7.
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    private void setPersonInsert(Role role,User user)
    {
        Date d = new Date();
        role.setCreatePerson(user.getUsername());
        role.setUpdatePerson(user.getUsername());
        role.setCreateDate(d);
        role.setUpdateDate(d);
    }
    private void setPersonUpdate(Role role,User user)
    {
        Date d = new Date();
        role.setUpdatePerson(user.getUsername());
        role.setUpdateDate(d);
    }

    public Role getRoleById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    public Integer createRole(Role role, User user) {
        setPersonInsert(role,user);
        return roleMapper.insertSelective(role);
    }

    public Integer updateRoleById(Role role, User user) {
        setPersonUpdate(role,user);
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    public Integer deleteRoleById(Integer id) {
        return roleMapper.deleteRoleById(id);
    }

    public List<Role> getRoleListBySearch(RoleSearch search) {
        return roleMapper.getRoleListBySearch(search,new RowBounds((search.getPageNo() - 1) * search.getPageSize(), search.getPageSize()));
    }

    public Integer getRoleTotalBySearch(RoleSearch searchModel) {
        return roleMapper.getRoleTotalBySearch(searchModel);
    }

    public List<Role> getRoleListByUserId(Integer userId) {
        return roleMapper.getRoleListByUserId(userId);
    }

    public Boolean isUsedByUser(Integer roleId) {
        return roleMapper.isUsedByUser(roleId);
    }

    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }

    public String getRoleDataTables(RoleSearch searchModel) {
        Integer total = getRoleTotalBySearch(searchModel);
        List<Role> roleList = getRoleListBySearch(searchModel);
        if(roleList==null || roleList.size()==0)
        {
            return "{\"iTotalRecords\":0,\"iTotalDisplayRecords\":0,\"aaData\":[]}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("{\"iTotalRecords\":%d,\"iTotalDisplayRecords\":%d,\"aaData\":[",total,total));
        int i= 0;
        for(Role r:roleList)
        {
            if(i != 0) sb.append(",");
            addDataRow(sb,r);
            i++;
        }
        sb.append("]}");
        return sb.toString();
    }

    public String getRoleDataRow(Integer id) {
        Role r = getRoleById(id);
        StringBuilder sb = new StringBuilder();
        addDataRow(sb,r);
        return sb.toString();
    }

    public String getRoleForOptions(Integer userId) {
        List<Role> assignRoles = getRoleListByUserId(userId);
        List<Role> allRoles = getRoleList();

        Map<Integer,Role> hmAssignRoles = new HashMap<Integer,Role>();
        for(Role r:assignRoles)
        {
            hmAssignRoles.put(r.getId(),r);
        }
        StringBuilder sb = new StringBuilder();
        for(Role r:allRoles)
        {
            sb.append("<option value=\"").append(r.getId()).append("\"");
            if(hmAssignRoles.containsKey(r.getId()))
            {
                sb.append(" selected");
            }
            sb.append(">").append(r.getName()).append("</option>");
        }
        return sb.toString();
    }

    public String assignPermission(Integer roleId, String checkedStr) {
        String[] checkedArr = checkedStr.split(",");
        List<Integer> resourceIds = new ArrayList<Integer>();
        for(String s:checkedArr) {
            resourceIds.add(Integer.parseInt(s));
        }

        roleMapper.deleteRoleById(roleId);
        if(resourceIds.size()>0)
        {
            roleMapper.assignPermissions(resourceIds, roleId);
        }

        return ConstantUtil.Success;
    }

    private void addDataRow(StringBuilder sb,Role r)
    {
        sb.append("[");
        sb.append("\"<input type=\\\"checkbox\\\" name=\\\"id[]\\\" value=\\\"").append(r.getId()).append("\\\">\"");
        sb.append(",").append(r.getId());
        sb.append(",\"").append(r.getName()).append("\"");
        sb.append(",\"").append(r.getRemark()).append("\"");
        sb.append(",\"").append(r.getUpdatePerson()).append("\"");
        sb.append(",\"").append(StringUtil.formatDate(r.getUpdateDate(),"yyyy-MM-dd HH:mm:ss")).append("\"");
        sb.append(",\"")
                .append("<a href=\\\"javascript:Role.update_click('").append(r.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-edit\\\"></i> 修改</a>")
                .append("&nbsp;&nbsp;<a href=\\\"javascript:Role.remove('").append(r.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-times\\\"></i> 删除</a>")
                .append("&nbsp;&nbsp;<a href=\\\"javascript:Role.assign_click('").append(r.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-key\\\"></i> 分配权限</a>")
                .append("\"");
        sb.append("]");
    }
}
