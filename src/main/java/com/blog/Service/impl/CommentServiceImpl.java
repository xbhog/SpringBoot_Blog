package com.blog.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.Dao.BlogDao;
import com.blog.Dao.CommentDao;
import com.blog.Pojo.Comment;
import com.blog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl  extends ServiceImpl<CommentDao, Comment> implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> getCommentByBlogId(Long blogId) {
        //通过网页传过来的id,找到对应的评论
        List<Comment> comments = commentDao.findByBlogAndParentCommentNull(blogId,Long.parseLong("-1"));
        return comments;
    }

    @Override
    public int saveComment(Comment comment) {  //保存回复的评论
        //获得父id
        Long id = comment.getParentComment().getId();
        if(id != -1){
            //有父评论
            comment.setParentComment(commentDao.findByParentCommentId(comment.getParentCommentId()));
        }else{
            //没有父评论
            comment.setParentCommentId(-1L);
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentDao.saveComment(comment);
    }
}
