package com.lz.privilegem.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lz.privilegem.biz.Interface.IUserService;
import com.lz.privilegem.entity.User;
import com.lz.privilegem.entity.UserSearch;
import com.lz.privilegem.mapper.UserMapper;
import com.lz.privilegem.utils.ConstantUtil;
import com.lz.privilegem.utils.StringUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by lizhi on 2017/7/7.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    private void setPersonInsert(User user,User operator)
    {
        Date d = new Date();
        user.setIsLock(false);
        user.setIsDelete(false);
        user.setCreatePerson(operator.getUsername());
        user.setUpdatePerson(operator.getUsername());
        user.setCreateDate(d);
        user.setUpdateDate(d);
    }
    private void setPersonUpdate(User user,User operator)
    {
        Date d = new Date();
        user.setUpdatePerson(operator.getUsername());
        user.setUpdateDate(d);
    }

    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public Integer getUserTotalBySearch(UserSearch search) {
        return userMapper.getUserTotalBySearch(search);
    }

    public List<User> getUserListBySearch(UserSearch search) {
        return userMapper.getUserListBySearch(search,new RowBounds((search.getPageNo() - 1) * search.getPageSize(), search.getPageSize()));
    }

    public Integer createUser(User user, User operator) {
        setPersonInsert(user,operator);
        user.setPassword(StringUtil.makeMD5(user.getPassword()));
        return userMapper.insertSelective(user);
    }

    public Integer updateUserById(User user, User operator) {
        setPersonUpdate(user,operator);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional
    public Integer deleteUserById(Integer id, User operator) {
        User user = getUserById(id);
        if(user==null)
        {
            return 0;
        }

        userMapper.deleteUserRoleById(id);
        user.setIsDelete(true);
        setPersonUpdate(user,operator);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional
    public String assignRole(Integer id, String selectedStr) {
        String[] selectedArr = selectedStr.split(",");
        List<Integer> roleIds = new ArrayList<Integer>();

        for(String s:selectedArr)
        {
            if(StringUtils.hasText(s))
            {
                roleIds.add(Integer.parseInt(s));
            }
        }

        userMapper.deleteUserRoleById(id);
        if(roleIds.size()>0)
        {
            userMapper.assignRoles(roleIds, id);
        }

        return ConstantUtil.Success;
    }

    public String getUserDataTables(UserSearch searchModel) {
        Integer total = getUserTotalBySearch(searchModel);
        List<User> userList = getUserListBySearch(searchModel);
        if(userList==null || userList.size()==0)
        {
            return "{\"iTotalRecords\":0,\"iTotalDisplayRecords\":0,\"aaData\":[]}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("{\"iTotalRecords\":%d,\"iTotalDisplayRecords\":%d,\"aaData\":[",total,total));
        int i= 0;
        for(User u:userList)
        {
            if(i != 0) sb.append(",");
            addDataRow(sb,u);
            i++;
        }
        sb.append("]}");
        return sb.toString();
    }

    public String getUserDataRow(Integer id) {
        User u = getUserById(id);
        StringBuilder sb = new StringBuilder();
        addDataRow(sb,u);
        return sb.toString();
    }

    public boolean checkPassword(String username, String password) {
        return true;
    }

    private void addDataRow(StringBuilder sb,User u)
    {
        sb.append("[");
        sb.append("\"<input type=\\\"checkbox\\\" name=\\\"id[]\\\" value=\\\"").append(u.getId()).append("\\\">\"");
        sb.append(",").append(u.getId());
        sb.append(",\"").append(u.getUsername()).append("\"");
        sb.append(",\"").append(u.getFullname()).append("\"");
        sb.append(",\"").append(u.getGender()?"男":"女").append("\"");
        sb.append(",\"").append(u.getIsAdmin()?"管理员":"普通").append("\"");
        sb.append(",\"").append(u.getIsLock()?"是":"否").append("\"");
        sb.append(",\"").append(u.getUpdatePerson()).append("\"");
        sb.append(",\"").append(StringUtil.formatDate(u.getUpdateDate(),"yyyy-MM-dd HH:mm:ss")).append("\"");
        sb.append(",\"")
                .append("<a href=\\\"javascript:User.update_click('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-edit\\\"></i> 修改</a>")
                .append("&nbsp;&nbsp;<a href=\\\"javascript:").append(u.getIsLock()?"User.unlock('":"User.lock('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-").append(u.getIsLock()?"un":"").append("lock\\\"></i> ").append(u.getIsLock()?"解锁":"锁定").append("</a>")
                .append("&nbsp;&nbsp;<a href=\\\"javascript:User.remove('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-times\\\"></i> 删除</a>")
                .append("&nbsp;&nbsp;<a href=\\\"javascript:User.assign_click('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-key\\\"></i> 分配角色</a>")
                .append("\"");
        sb.append("]");
    }
}
