package com.andrew.mysqltest.mapper;

import com.andrew.mysqltest.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserMapper {

    // 添加数据
    public int add(User user);

    // 查询数据
    public List<User> queryAll();


}
