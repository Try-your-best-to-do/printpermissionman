package com.hzxy.printpermissionman.service.impl;

import com.hzxy.printpermissionman.dao.ReviewMapper;
import com.hzxy.printpermissionman.model.Review;
import com.hzxy.printpermissionman.service.ApplyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/*
 * ye
 * 用户申请的实现
 * 2021.1.11
 * */

@Transactional
@Service
public class ApplyServiceImpl implements ApplyService {

    @Resource
    private ReviewMapper reviewMapper;

    @Override
    public int apply(Review review) {
        Review review1 = reviewMapper.selectByPrimaryKey(review.getUser());
        if (review1==null){
            int temp = reviewMapper.insert(review);
            return temp;
        }else {
            Long diff = review.getDate().getTime() - review1.getDate().getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            if (diffDays>=30.0){
                int temp = reviewMapper.updateByPrimaryKey(review);
                return temp;
            }else {
                return -1;
            }
        }

    }
}
