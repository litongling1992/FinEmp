/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: UserController
 * Author:   Administrator
 * Date:     2020/8/30 13:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.baizhi.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baizhi.entity.Emp;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.service.impl.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/8/30
 * @since 1.0.0
 */
@RestController
@CrossOrigin //允许跨域
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    /*
     *  用户注册
     *  @RequestBody加上这个注解才能让返回的数据解析成Json字符串
     *  因为axios以json来发送数据
     * */

    @PostMapping("register") //PostMapping 传递过来的参数是一个对象信息,参数加上@RequestBody 是为了让传递的参数解析成json
    public Map<String,Object> reginster(@RequestBody User user){

        log.info("用户信息：[{}]",user.toString());

        Map<String,Object> map = new HashMap<>();
        try {
            //调用业务方法进行注册
            userService.reginster(user);
            map.put("state",true);
            map.put("msg","提示信息：注册成功！");
        }catch (Exception e){
            map.put("state",false);
            map.put("msg","提示信息：注册失败！"+e.getMessage());
        }
        return map;
    }

    @PostMapping("login")
    public Map<String,Object> login(@RequestBody User user){
        Map<String,Object> map = new HashMap<>();

        try {
            User userDb = userService.login(user);
            map.put("state",true);
            map.put("msg","提示信息：登录成功！");
            map.put("user",userDb);

          //  String token = "Bearer"+tokenService.getToken(userDb);

            JwtBuilder builder = Jwts.builder().claim("roles", userDb.getRoles()).claim("username", userDb
                    .getUsername()).claim("realname", userDb.getRealname());
            String token = builder.compact();

            String c =parseJWT(token);
           log.info("roles:"+c);

            map.put("token",token);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","提示信息：登录失败！"+e.getMessage());
        }

        return map;
    }

    public String parseJWT(String token){

        Algorithm algorithm = Algorithm.none();
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        //DecodedJWT jwt = verifier.verify(token);

       // DecodedJWT d=JWT.decode(token);
        Claim a =jwt.getClaim("roles");
        String b =a.asString();
       // String t= d.getToken();
        return a.asString();
    }


    //查询所有员工信息
    @GetMapping("findAll")
    public Map<String,Object> findAll(){
        Map<String,Object> map = new HashMap<>();
        try {
            //调用业务方法进行注册
       List<User> users =    userService.findAll();
            map.put("state",true);
            map.put("msg","提示信息：获取所有用户成功！");
            map.put("users",users);
        }catch (Exception e){
            map.put("state",false);
            map.put("msg","获取所有用户失败！"+e.getMessage());
        }
        return map;
    }


    @PostMapping("update") //PostMapping 传递过来的参数是一个对象信息,参数加上@RequestBody 是为了让传递的参数解析成json
    public Map<String,Object> updateOne(@RequestBody User user){

        log.info("用户信息：[{}]",user.toString());

        Map<String,Object> map = new HashMap<>();
        try {
            User tempUser = new User();
            User userDb = userService.findUserByUserName(user.getUsername());
            if (userDb !=null){
                tempUser.setId(userDb.getId());
                tempUser.setUsername(userDb.getUsername());
                tempUser.setPassword(user.getPassword());
                tempUser.setRealname(userDb.getRealname());
                tempUser.setRoles(userDb.getRoles());
                tempUser.setRegsterTime(userDb.getRegsterTime());
                userDb.setPassword(user.getPassword());
                userService.updateOne(userDb);
                map.put("state",true);
                map.put("msg","提示信息：修改密码成功！");

            }
            else {
                map.put("state",false);
                map.put("msg","提示信息：用户名不存在");
            }

        }catch (Exception e){
            map.put("state",false);
            map.put("msg","提示信息：修改密码失败！"+e.getMessage());
        }
        return map;
    }




}
