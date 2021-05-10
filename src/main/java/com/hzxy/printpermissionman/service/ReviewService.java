package com.hzxy.printpermissionman.service;

import com.github.pagehelper.Page;
import com.hzxy.printpermissionman.model.Review;

import java.util.List;

public interface ReviewService {

    Page<Review> findAllReviews();

    int reviewApplyS(String name);

    int reviewApplyF(String name);
}
