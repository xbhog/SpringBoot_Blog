package com.blog.Pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@TableName(value ="t_blog")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private Long id;
    /*基础属性*/
    private String title;
    private String content;
    private String firstPicture;  //详情页图片
    private String flag; //转载还是原创
    private Integer views; //浏览数

    /*开关设置*/
    private boolean appreciation; //赞赏是否开启
    private boolean shareStatement; //版权是否开启
    private boolean commentabled; //评论是否开启
    private boolean published; //是否发布
    private boolean recommend; //是否推荐
    /*时间戳*/
    private Date createTime;
    private Date updateTime;
    private String description;
    /*处理实体类中的关系
    * 这个属性用来在mybatis中进行连接查询的
    * */
    private Long typeId;
    private Long userId;
    private String tagIds;
    @TableField(exist = false)
    private Type type;  //一个博客对应一个分类

    @TableField(exist = false)
    private User user;  //一个博客对应一个作者

    @TableField(exist = false)
    private List<Tag> tags = new ArrayList<>();  //一个博客对应多个标签

    @TableField(exist = false)
    private List<Comment> comments = new ArrayList<>();  //一个博客对应多个评论

}
