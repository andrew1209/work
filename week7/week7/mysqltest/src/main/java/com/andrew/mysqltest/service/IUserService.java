package com.andrew.mysqltest.service;

import com.andrew.mysqltest.entity.User;

import java.util.List;

public interface IUserService {

    // 添加数据
    public int add(User user);

    // 查询数据
    public List<User> findAll();

}
