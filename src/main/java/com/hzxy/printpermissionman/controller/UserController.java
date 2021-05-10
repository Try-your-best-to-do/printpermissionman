package com.hzxy.printpermissionman.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzxy.printpermissionman.model.Review;
import com.hzxy.printpermissionman.model.User;
import com.hzxy.printpermissionman.service.ApplyService;
import com.hzxy.printpermissionman.service.UserService;
import com.hzxy.printpermissionman.utils.Code;
import com.hzxy.printpermissionman.utils.ResultInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
* ye
* 用户控制器
* 2021.1.11
*
* */


@Controller
public class UserController {

    Logger logger = LogManager.getLogger(this.getClass());
    private ResultInfo info;
    @Resource
    private UserService userService;

    @Resource
    private ApplyService applyService;
    @ModelAttribute
    public void init() {
        info = new ResultInfo();
    }


    //注册
    @ResponseBody
    @RequestMapping("/user/register")
    public ResultInfo register(User user){
        if (user.getUsername().equals("")||user.getPassword().equals("")){
            info.setCode(Code.NODATA);
            return info;
        }

        try{
            int temp = userService.register(user);
            if (temp == 1){
                info.setData(temp);
                return info;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        info.setCode(Code.DATAEXIST);
        return info;
    }

    //登录
    @ResponseBody
    @RequestMapping("/user/login")
    public ResultInfo login(User user){
        if (user.getUsername()==null||user.getUsername().equals("")){
            info.setData(Code.LOGINERR);
            return info;
        }
        try {
//            logger.info(user1);
            HashMap<String,User> userHashMap = new HashMap<>();
            User user1 = userService.login(user.getUsername(),user.getPassword(),user.getRole());
            userHashMap.put("user",user1);
            info.setData(userHashMap);
            return info;
        }catch (Exception e){
            e.printStackTrace();
        }
        info.setData(Code.LOGINERR);
        return info;
    }


    //提交申请
    @ResponseBody
    @RequestMapping("/user/apply")
    public ResultInfo apply(Review review){
        if (review.getUser().equals("")){
            info.setCode(Code.NODATA);
            return info;
        }

        try{
            int temp = applyService.apply(review);
            if (temp == 1){
                info.setData(temp);
                return info;
            }else if (temp == -1){
                info.setMsg("一个月只能申请一次！");
                return info;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        info.setCode(Code.DATAEXIST);
        return info;
    }



    //查找所有用户
    @ResponseBody
    @RequestMapping("/user/findAllUsers")
    public ResultInfo findAllUsers(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize){
        try{
            PageHelper.startPage(pageNo,pageSize);
            PageInfo<User> pageInfo = new PageInfo<>(userService.findAllUsers());
            List<User> users = pageInfo.getList();
            if (users.size()<=0){
                info.setCode(Code.NODATA);
                return info;
            }
            HashMap<String,Object> userHashMap = new HashMap<>();
            userHashMap.put("users",users);
            info.setData(userHashMap);
            return info;
        }catch (Exception e){
            e.printStackTrace();
        }
        info.setCode(Code.NODATA);
        return info;
    }


    //删除用户
    @ResponseBody
    @RequestMapping("/user/deleteUser")
    public ResultInfo deleteUser(String user){
        try{
            if (user.equals("")){
                info.setCode(Code.NODATA);
                return info;
            }
            int temp = userService.deleteUser(user);
            info.setData(temp);
            return info;
        }catch (Exception e){
            e.printStackTrace();
        }
        info.setCode(Code.DATAERR);
        return info;
    }


    //查找所有用户
    @ResponseBody
    @RequestMapping("/user/findByName")
    public ResultInfo findByName(String name){
        try{
            User user = userService.findByName(name);
            if (user==null){
                info.setCode(Code.NODATA);
                return info;
            }
            HashMap<String,Object> userHashMap = new HashMap<>();
            userHashMap.put("user",user);
            info.setData(userHashMap);
            return info;
        }catch (Exception e){
            e.printStackTrace();
        }
        info.setCode(Code.NODATA);
        return info;
    }
}
