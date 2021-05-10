package com.hzxy.printpermissionman.service;

import com.github.pagehelper.Page;
import com.hzxy.printpermissionman.model.User;

import java.util.List;

/*
 * ye
 * 用户的service接口
 * 2021.1.11
 * */

public interface UserService {
    int register(User user);

    User login(String username,String password,String role);

    Page<User> findAllUsers();



    int deleteUser(String username);

    User findByName(String name);
}
