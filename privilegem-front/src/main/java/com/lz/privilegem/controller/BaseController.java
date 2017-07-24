package com.lz.privilegem.controller;

import com.lz.privilegem.entity.Permission;
import com.lz.privilegem.entity.User;
import com.lz.privilegem.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhi on 2017/7/7.
 */

@Controller
@RequestMapping("/controller")
public class BaseController {

    /**
     * dashboard页
     */
    @RequestMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
