package com.blog.Controller;

import com.blog.Pojo.Blog;
import com.blog.Pojo.Comment;
import com.blog.Pojo.User;
import com.blog.Service.BlogService;
import com.blog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;
    //显示评论
    @GetMapping("/comments/{blogId}")
    public String toComments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.getCommentByBlogId(blogId));

        model.addAttribute("blog",blogService.getDetailBlog(blogId));
        return "blog :: commentList";
    }

    //提交评论
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        System.out.println("进入--------");
        Long id = comment.getBlog().getId();
        comment.setBlog(blogService.getDetailBlog(id));
        comment.setBlogId(id);
        User user = (User) session.getAttribute("user");
        if(user != null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else{
            comment.setAvatar(null);
        }
        commentService.saveComment(comment);
        return "redirect:/comments"+id;
    }
}
