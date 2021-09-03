package com.blog.Pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@TableName(value="t_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    private  Long id;
    private String name;


    /*处理实体类中的关系*/
    //blog m --- 1 type
    @TableField(exist = false)
    private List<Blog> blogs = new ArrayList<>();
}
