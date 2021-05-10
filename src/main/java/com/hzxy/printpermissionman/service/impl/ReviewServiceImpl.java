package com.hzxy.printpermissionman.service.impl;

import com.github.pagehelper.Page;
import com.hzxy.printpermissionman.dao.ReviewMapper;
import com.hzxy.printpermissionman.dao.UserMapper;
import com.hzxy.printpermissionman.model.Review;
import com.hzxy.printpermissionman.model.User;
import com.hzxy.printpermissionman.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
/*
 * ye
 * 管理员的审核实现
 * 2021.1.11
 * */

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Resource
    private ReviewMapper reviewMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public Page<Review> findAllReviews() {
        return reviewMapper.selectAll();
    }

    @Override
    public int reviewApplyS(String name) {
        Review review1 = reviewMapper.selectByPrimaryKey(name);
        review1.setStatus("success");
        int temp = reviewMapper.updateByPrimaryKey(review1);
        if (temp==1){
            User user = userMapper.selectByPrimaryKey(review1.getUser());
            user.setBlack(review1.getBlack());
            user.setColor(review1.getColor());
            int flag = userMapper.updateByPrimaryKey(user);
            return flag;
        }else {
            return 0;
        }
    }

    @Override
    public int reviewApplyF(String name) {
        Review review1 = reviewMapper.selectByPrimaryKey(name);
        review1.setStatus("fail");
        int temp = reviewMapper.updateByPrimaryKey(review1);
        return temp;
    }


}
