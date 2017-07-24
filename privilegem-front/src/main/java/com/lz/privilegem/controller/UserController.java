package com.lz.privilegem.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.lz.privilegem.biz.Interface.IRoleService;
import com.lz.privilegem.entity.Permission;
import com.lz.privilegem.entity.UserSearch;
import com.lz.privilegem.mapper.PermissionMapper;
import com.lz.privilegem.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.lz.privilegem.contract.IBaseApiService;
import com.lz.privilegem.biz.Interface.IUserService;
import com.lz.privilegem.entity.User;
import org.springframework.web.servlet.ModelAndView;

/**
 *用户管理相关的控制器
 */
@Controller
@RequestMapping("controller/user")
public class UserController {

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    /**
     * 用户登录
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, BindingResult result, Model model, HttpServletRequest request) {
        try {
            // 身份验证
            userService.checkPassword(user.getUsername(), user.getPassword());
            // 验证成功在Session中保存用户信息
            final User authUserInfo = userService.getUserByUsername(user.getUsername());
            SessionUtil.setSessionUser(request, authUserInfo);
        } catch (Exception e) {
            // 身份验证失败
            model.addAttribute("error", "用户名或密码错误 ！");
            return "login";
        }

        return "index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) throws Exception
    {
        SessionUtil.clearSession(request);
        return  "login";
    }


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String  main(Model model) throws Exception{
        UserSearch userSearch = new UserSearch();
        List<User> userList = userService.getUserListBySearch(userSearch);
        model.addAttribute("users", userList);
        return "user/list";
    }

    @ResponseBody
    @RequestMapping("/get")
    public String  get(@RequestParam("id") Integer id) throws Exception{
        User user = userService.getUserById(id);
        return JsonUtil.convertObj2json(user).toString();
    }

    @RequestMapping("/add")
    public String addform(ModelMap map) {
        return "user/addform";
    }

    @ResponseBody
    @RequestMapping("/add")
    public String add(@ModelAttribute("user")User user,HttpServletRequest request) {
        //从session取出User对象
        User operator = SessionUtil.getSessionUser(request);
        userService.createUser(user, operator);
        return ConstantUtil.Success;
    }

    @RequestMapping("/updateform")
    public String updateform(Integer id,HttpServletRequest request,ModelMap map) {
        User user = userService.getUserById(id);
        map.put("user", user);
        return "user/updateform";
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute("user")  User user, HttpServletRequest request) {
        //从session取出User对象
        User operator = SessionUtil.getSessionUser(request);

        userService.updateUserById(user,operator);

        return ConstantUtil.Success;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Integer id, HttpServletRequest request){

        //从session取出User对象
        User operator = SessionUtil.getSessionUser(request);

        User user = new User();
        user.setId(id);
        user.setIsDelete(true);

        userService.updateUserById(user, operator);

        return ConstantUtil.Success;
    }
    @ResponseBody
    @RequestMapping("/resetpass")
    public String resetpass(@RequestParam("id") Integer id,HttpServletRequest request){

        //从session取出User对象
        User operator = SessionUtil.getSessionUser(request);

        User user = new User();
        user.setId(id);
        user.setPassword(ConstantUtil.DefaultMd5Password);

        userService.updateUserById(user, operator);

        return ConstantUtil.Success;
    }

    @ResponseBody
    @RequestMapping("/lock")
    public String lock(@RequestParam("id") Integer id,HttpServletRequest request){

        //从session取出User对象
        User operator = SessionUtil.getSessionUser(request);

        User user = new User();
        user.setId(id);
        user.setIsLock(true);

        userService.updateUserById(user, operator);

        return ConstantUtil.Success;
    }

    @ResponseBody
    @RequestMapping("/unlock")
    public String unlock(@RequestParam("id") Integer id,HttpServletRequest request){

        //从session取出User对象
        User operator = SessionUtil.getSessionUser(request);

        User user = new User();
        user.setId(id);
        user.setIsLock(false);

        userService.updateUserById(user, operator);

        return ConstantUtil.Success;
    }

    @RequestMapping("/assignform")
    public String assignform(Integer id,ModelMap map) {
        map.put("options", roleService.getRoleForOptions(id));
        map.put("id", id);
        return "user/assignform";
    }

    @ResponseBody
    @RequestMapping("/assign")
    public String assign(Integer id,String selectedStr)
    {
        if(id==null || StringUtil.isStrEmpty(id.toString()) || StringUtil.isStrEmpty(selectedStr))
        {
            return ConstantUtil.Fail;
        }
        userService.assignRole(id, selectedStr);

        return ConstantUtil.Success;
    }
}
