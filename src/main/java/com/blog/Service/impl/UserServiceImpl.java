package com.blog.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.Dao.UserCheck;
import com.blog.Pojo.User;
import com.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserCheck userCheck;

    @Override
    public User checkUser(String username, String password) {
        User user = userCheck.queryByUser(username, password);
        return user;
    }
}
