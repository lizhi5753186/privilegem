package com.lz.privilegem.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lz.privilegem.entity.Permission;
import com.lz.privilegem.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lz.privilegem.contract.IBaseApiService;
import com.lz.privilegem.biz.Interface.IUserService;
import com.lz.privilegem.entity.User;
import com.lz.privilegem.utils.ConfigUtil;
import com.lz.privilegem.utils.ConstantUtil;
import com.lz.privilegem.utils.SessionUtil;
import com.lz.privilegem.utils.StringUtil;

@Controller
@RequestMapping("/controller")
public class IndexController {

    /**
     * 首页
     *
     * @param request
     * @return
     */
    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        return "index";
    }
}
