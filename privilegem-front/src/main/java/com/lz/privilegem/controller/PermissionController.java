package com.lz.privilegem.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lz.privilegem.biz.Interface.IPermissionService;
import com.lz.privilegem.entity.Permission;
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
@RequestMapping("/controller/permission")
public class PermissionController {

    @Autowired
    private ConfigUtil configUtil;
    @Autowired
    private IPermissionService permissionService;

    @ResponseBody
    @RequestMapping("/getPermissionTree")
    public String getResourceTree(HttpServletRequest request, HttpServletResponse response, ModelMap map) {

        //这是为了jstree插件使用，这个插件只对Content-Type为json和html的内容进行处理
        response.setContentType("application/json;charset=UTF-8");

        return permissionService.getPermissionTree(0);
    }

    @ResponseBody
    @RequestMapping("/getResourceTreeWithChecked")
    public String getResourceTreeWithChecked(Integer roleId,HttpServletRequest request,HttpServletResponse response,ModelMap map) {

        //这是为了jstree插件使用，这个插件只对Content-Type为json和html的内容进行处理
        response.setContentType("application/json;charset=UTF-8");

        return permissionService.getPermissionTree(roleId);
    }

    @ResponseBody
    @RequestMapping("/get")
    public String get(Integer id,ModelMap map) {

        Permission permission = permissionService.getPermissionById(id);
        return JsonUtil.convertObj2json(permission).toString();
    }

    @ResponseBody
    @RequestMapping("/add")
    public String add(@ModelAttribute("permission")Permission permission, HttpServletRequest request) {

        //从session取出User对象
        User user = SessionUtil.getSessionUser(request);
        //生成节点
        permissionService.createPermission(permission, user);
        return JsonUtil.convertObj2json(permission).toString();
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute("permission")  Permission permission,HttpServletRequest request) {
        //从session取出User对象
        User user = SessionUtil.getSessionUser(request);

        //生成节点积累
        permissionService.updatePermissionById(permission, user);

        return JsonUtil.convertObj2json(permission).toString();
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String  delete(@RequestParam("id") Integer id) throws Exception{

        //判断节点是否被用户关联
        if(permissionService.isUsedByRole(id))
        {
            return ConstantUtil.Fail;
        }

        permissionService.deletePermissionById(id);
        return ConstantUtil.Success;
    }
}
