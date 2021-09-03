package com.blog.Service;

import com.blog.Pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentByBlogId(Long blogId);
    int saveComment(Comment comment);
}
