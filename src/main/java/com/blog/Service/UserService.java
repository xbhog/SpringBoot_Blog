package com.blog.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.Pojo.User;


public interface UserService {
    public User checkUser(String username,String password);
}
