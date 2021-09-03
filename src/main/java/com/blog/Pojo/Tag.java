package com.blog.Pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@TableName(value="t_tag")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private Long id;
    private String name;

    /*处理实体类中的关系*/
    /*多对多的关系*/
    @TableField(exist = false)
    private List<Blog> blogs = new ArrayList<>();  //多个博客
}
