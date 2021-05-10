package com.hzxy.printpermissionman.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzxy.printpermissionman.model.Review;
import com.hzxy.printpermissionman.service.ReviewService;
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
import java.util.HashMap;
import java.util.List;

/*
 * ye
 * 审核控制器
 * 2021.1.11
 *
 * */
@Controller
public class ReviewController {

    Logger logger = LogManager.getLogger(this.getClass());
    private ResultInfo info;

    @ModelAttribute
    public void init() {
        info = new ResultInfo();
    }

    @Resource
    private ReviewService reviewService;

    //审核通过
    @ResponseBody
    @RequestMapping("/review/pass")
    public ResultInfo reviewPass(String name){
        try{
            int temp = reviewService.reviewApplyS(name);
            if (temp==1){
                info.setData(temp);
                return info;
            }else {
                info.setData(Code.UNKOWNERR);
                return info;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        info.setData(Code.UNKOWNERR);
        return info;
    }

    //审核不通过
    @ResponseBody
    @RequestMapping("/review/fail")
    public ResultInfo reviewFail(String name){
        try{
            int temp = reviewService.reviewApplyF(name);
            if (temp==1){
                info.setData(temp);
                return info;
            }else {
                info.setData(Code.UNKOWNERR);
                return info;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        info.setData(Code.UNKOWNERR);
        return info;
    }

    //审核不通过
    @ResponseBody
    @RequestMapping("/review/findAllReviews")
    public ResultInfo findAll(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize){
        try{
            HashMap<String,Object> hashMap = new HashMap<>();
            PageHelper.startPage(pageNo,pageSize);
            PageInfo<Review> pageInfo = new PageInfo<>(reviewService.findAllReviews());
            List<Review> reviews= pageInfo.getList();
            hashMap.put("reviews",reviews);
            info.setData(hashMap);
            return info;
        }catch (Exception e){
            e.printStackTrace();
        }
        info.setData(Code.NODATA);
        return info;
    }
}
