package com.blog.Pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@TableName(value="t_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;
    private String nickname; //昵称
    private String email; //邮箱
    private String content; //评论内容
    private String avatar; //评论者的头像
    private Date createTime;  //评论的时间
    private boolean adminComment; //是否为管理员评论；

    //父评论信息
    private Long blogId;
    private Long parentCommentId; //父评论id
    @TableField(exist = false)
    private String parentNickname;

    /*处理实体类中的关系*/
    @TableField(exist = false)
    private Blog blog;
    /*从父类来考虑*/
    @TableField(exist = false)
    private List<Comment> replayComments = new ArrayList<>();   //回复的子类对象  多个子类对象对应一个父类对象
    @TableField(exist = false)
    private Comment parentComment;  //父类对象  一个子类对象对应一个父类对象
}
