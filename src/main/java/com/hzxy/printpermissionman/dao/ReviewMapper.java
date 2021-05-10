package com.hzxy.printpermissionman.dao;

import com.github.pagehelper.Page;
import com.hzxy.printpermissionman.model.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 * ye
 * 审核dao
 * 2021.1.11
 *
 * */

@Mapper
public interface ReviewMapper {
    int deleteByPrimaryKey(String user);

    int insert(Review record);

    int insertSelective(Review record);

    Review selectByPrimaryKey(String user);

    int updateByPrimaryKeySelective(Review record);

    int updateByPrimaryKey(Review record);

    Page<Review> selectAll();

}