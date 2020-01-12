package com.yc.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yc.entity.User;
import com.yc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yuche on 2019/3/26.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void addUser(User user) {
        user.setCreateTime(new Date());
        userMapper.insert(user);
    }

    public List<User> select(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("user_name", username);
        return userMapper.selectList(queryWrapper);
    }

}
