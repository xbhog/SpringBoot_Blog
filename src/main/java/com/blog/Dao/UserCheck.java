package com.blog.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.Pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserCheck{
    public User queryByUser(@Param("username") String username,@Param("password") String password);
}
