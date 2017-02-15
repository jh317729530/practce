package com.gunn.controller;

import com.gunn.entity.User;
import com.gunn.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Gunn on 2017/2/15.
 */

@RestController
public class UserConrtoller {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/getUser")
    public User getUser(Integer id){
        User user=userMapper.selectUserById(id);
        return user;
    }
}
