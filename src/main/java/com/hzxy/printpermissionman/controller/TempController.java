package com.hzxy.printpermissionman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * ye
 * 页面跳转控制器
 * 2021.1.11
 *
 * */

@Controller
public class TempController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/features")
    public String features(){
        return "features";
    }

    @GetMapping("/user/index")
    public String userIndex(){
        return "user/index";
    }

    @GetMapping("/user/reqacc")
    public String userreQacc(){
        return "user/reqacc";
    }

    @GetMapping("/admin/index")
    public String adminIndex(){
        return "admin/index";
    }

    @GetMapping("/admin/userman")
    public String adminUserman(){
        return "admin/userman";
    }

    @GetMapping("/admin/userreview")
    public String adminUserReview(){
        return "admin/userreview";
    }

    @GetMapping("/admin/inventoryman")
    public String adminInventoryMan(){
        return "admin/inventoryman";
    }
}
