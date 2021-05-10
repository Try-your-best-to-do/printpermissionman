package com.hzxy.printpermissionman.service.impl;

import com.github.pagehelper.Page;
import com.hzxy.printpermissionman.dao.UserMapper;
import com.hzxy.printpermissionman.model.User;
import com.hzxy.printpermissionman.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/*
 * ye
 * 用户的service实现
 * 2021.1.11
 * */


@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int register(User user) {
        int temp = userMapper.insert(user);
        return temp;
    }

    @Override
    public User login(String username, String password, String role) {
        User user = userMapper.selectByPrimaryKey(username);
        if (user.getPassword().equals(password)&&user.getRole().equals(role)){
            return user;
        }else {
            return null;
        }
    }

    @Override
    public Page<User> findAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public int deleteUser(String username) {
        int temp = userMapper.deleteByPrimaryKey(username);
        return temp;
    }

    @Override
    public User findByName(String name) {
        User user = userMapper.selectByPrimaryKey(name);
        return user;
    }


}
