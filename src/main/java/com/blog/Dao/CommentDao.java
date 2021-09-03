package com.blog.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.Pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDao extends BaseMapper<Comment>{
    //根据创建时间倒叙来排
    List<Comment> findByBlogAndParentCommentNull(@Param("blogId") Long blogId,@Param("blogParentId") Long blogParentId);
    //查询父级对象
    Comment findByParentCommentId(@Param("parentCommentId") Long parentCommentId);

    //添加一个评论
    int saveComment(Comment comment);
}
