package com.hzxy.printpermissionman.dao;

import com.github.pagehelper.Page;
import com.hzxy.printpermissionman.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 * ye
 * 用户dao
 * 2021.1.11
 *
 * */

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String username);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    Page<User> selectAll();
}