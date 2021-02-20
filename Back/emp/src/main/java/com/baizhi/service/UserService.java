package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;

public interface UserService {

    //用户注册
    void reginster(User user);

    User findUserByUserName(String username);

    //用户登录

    User login(User user);

    User findUserById(String id);

    List<User> findAll();

    void updateOne(User user);
}
