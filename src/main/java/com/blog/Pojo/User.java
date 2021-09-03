package com.blog.Pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@TableName(value="t_user")  //对应数据库中的表名
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String nickname; //昵称
    private String username;
    private String password;
    private String email;

    private String avatar; //头像
    private String type; //类型

    /*时间戳*/
    private Date createTime;
    private Date updateTime;

    /*处理实体类中的关系*/
    private List<Blog> blogs = new ArrayList<>();  //多(blog)对1(user)
}
