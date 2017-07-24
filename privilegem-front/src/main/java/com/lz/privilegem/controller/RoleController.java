package com.lz.privilegem.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lz.privilegem.biz.Interface.IRoleService;
import com.lz.privilegem.entity.Permission;
import com.lz.privilegem.entity.Role;
import com.lz.privilegem.entity.RoleSearch;
import com.lz.privilegem.mapper.PermissionMapper;
import com.lz.privilegem.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lz.privilegem.contract.IBaseApiService;
import com.lz.privilegem.biz.Interface.IUserService;
import com.lz.privilegem.entity.User;

/**
 * Created by lizhi on 2017/7/7.
 */
@Controller
@RequestMapping("/controller/role")
public class RoleController  {

    @Autowired
    private ConfigUtil configUtil;
    @Autowired
    private IRoleService roleService;

    @ResponseBody
    @RequestMapping("/getRoleDataTables")
    public String getRoleDataTables(RoleSearch searchModel, ModelMap map) {
        return roleService.getRoleDataTables(searchModel);
    }

    @ResponseBody
    @RequestMapping("/getRoleDataRow")
    public String  getRoleDataRow(@RequestParam("id") Integer id) throws Exception{
        return roleService.getRoleDataRow(id);
    }

    @ResponseBody
    @RequestMapping("/get")
    public String  get(@RequestParam("id") Integer id) throws Exception{
        Role role = roleService.getRoleById(id);
        return JsonUtil.convertObj2json(role).toString();
    }

    @RequestMapping("/addform")
    public String addform() {
        return "role/addform.ftl";
    }

    @ResponseBody
    @RequestMapping("/add")
    public String add(@ModelAttribute("role")Role role,HttpServletRequest request) {
        //从session取出User对象
        User user = SessionUtil.getSessionUser(request);
        //生成节点角色
        if(role.getRemark() == null)
        {
            role.setRemark("");
        }
        roleService.createRole(role,user);
        return ConstantUtil.Success;
    }

    @RequestMapping("/updateform")
    public String updateform(Integer id,HttpServletRequest request,ModelMap map) {
        Role role = roleService.getRoleById(id);
        map.put("role", role);
        return "role/updateform.ftl";
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute("role") Role role, HttpServletRequest request) {
        //从session取出User对象
        User user = SessionUtil.getSessionUser(request);
        //生成节点角色
        roleService.updateRoleById(role,user);

        return ConstantUtil.Success;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String  delete(@RequestParam("id") Integer id) {

        //判断节点是否被用户关联
        if(roleService.isUsedByUser(id))
        {
            return ConstantUtil.Fail;
        }

        roleService.deleteRoleById(id);

        return ConstantUtil.Success;
    }
}
