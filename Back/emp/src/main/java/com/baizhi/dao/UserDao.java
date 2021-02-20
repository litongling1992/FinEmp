package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    void save(User user);

    User findByUserName(String username);

    User findUserById(String id);

    List<User> findAll();

    void updateOne(User user);
}
