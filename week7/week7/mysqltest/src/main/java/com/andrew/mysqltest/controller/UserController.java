package com.andrew.mysqltest.controller;

import com.andrew.mysqltest.entity.User;
import com.andrew.mysqltest.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/getUser")
    public String slave(){
        List<User> all = userService.findAll();
        return all.toString();
    }


    @RequestMapping("/add")
    public String add(){
        User user = new User().setName("123").setPassword("123123").setPhone("123123");
        userService.add(user);
        return "all.toString()";
    }

}
