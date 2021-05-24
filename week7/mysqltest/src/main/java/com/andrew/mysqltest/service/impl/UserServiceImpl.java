package com.andrew.mysqltest.service.impl;

import com.andrew.mysqltest.entity.User;
import com.andrew.mysqltest.mapper.UserMapper;
import com.andrew.mysqltest.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.queryAll();
    }
}
