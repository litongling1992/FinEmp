/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: TokenService
 * Author:   Administrator
 * Date:     2020/8/30 18:08
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.baizhi.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baizhi.entity.User;
import com.baizhi.utils.JwtCustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/8/30
 * @since 1.0.0
 */
@Service
public class TokenService {




    public String getToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
        JwtCustomUtils jwtUtil = new JwtCustomUtils();
//        token = JWT.create().withAudience(user.getId().toString()).withIssuedAt(start).withExpiresAt(end).withClaim
//                ("roles",user.getRoles())
//                .sign(Algorithm.HMAC256(user.getPassword()));
        String token1 = jwtUtil.createJWT(user.getId(),user.getUsername(), user.getRoles());
       // token = JWT.create().withClaim("roles",user.getRoles());
        return token1;
    }
}
