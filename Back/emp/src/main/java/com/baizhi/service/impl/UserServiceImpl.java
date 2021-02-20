/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: UserServiceImpl
 * Author:   Administrator
 * Date:     2020/8/30 13:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/8/30
 * @since 1.0.0
 */
@Service
@Transactional //事务的注解
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void reginster(User user){

        //根据用户名查找用户名是否已经注册
        User exutUser = userDao.findByUserName(user.getUsername());

        if (exutUser == null){
            user.setPassword("123");

            user.setRegsterTime(new Date());

            userDao.save(user);
        }else {
            throw new RuntimeException("用户名已经存在");
        }
    }

    @Override
    public User login(User user) {
        //第一步，检查用户名是否存在
        User userDb = userDao.findByUserName(user.getUsername());

        //如果用户名存在，再进行密码匹配。也可以使用ObjectUtils.isEmpty()进行比较
        if (userDb != null){
            //密码匹配
            if(userDb.getPassword().equals(user.getPassword())){
                return userDb;
            }else {
                throw new RuntimeException("密码输入错误") ;
            }

        }else {
            throw new RuntimeException("用户名输入错误") ;
        }
    }

    @Override
    public User findUserById(String id) {

       return userDao.findUserById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void updateOne(User user) {
        userDao.updateOne(user);
    }

    @Override
    public User findUserByUserName(String username) {
        return userDao.findByUserName(username);
    }
}
