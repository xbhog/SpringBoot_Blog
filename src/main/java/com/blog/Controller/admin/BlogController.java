package com.blog.Controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.Pojo.*;
import com.blog.Service.BlogService;
import com.blog.Service.TagService;
import com.blog.Service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;

    public void setTypeAndTag(Model model){
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
    }
    @GetMapping("blogs")
    public String blogsMain(@RequestParam(value="pn",defaultValue = "1") int pn,Model model){
        Page<Blog> blogPage = new Page<>(pn, 2);
        IPage<Blog> allBlogs = blogService.getAllBlogs(blogPage);
        List<Blog> records = allBlogs.getRecords();

        setTypeAndTag(model);  //查询类型和标签
        model.addAttribute("page",allBlogs);
        return "admin/blogs";
    }
    @PostMapping("/blogs/search")  //按条件查询博客
    public String searchBlogs(Blog blog, Model model,BlogQuery blogQuery,
                              @RequestParam(value="pn",defaultValue="1")int pn
                              ){
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(blogQuery.getTitle()),"title",blogQuery.getTitle());
        if(blogQuery.getRecommend() == true){
            queryWrapper.eq("recommend",blogQuery.getRecommend());
        }

        if(blogQuery.getTypeId() != null){
            queryWrapper.eq("type_id",blogQuery.getTypeId());
        }

        Page<Blog> blogPage = new Page<>(pn,2);

        Page<Blog> page = blogService.page(blogPage,queryWrapper);

        model.addAttribute("page",page);

        return "admin/blogs :: blogList";
    }
    @GetMapping("/blogs/input")
    public String toAddBlog(Model model){  //新增博客
        model.addAttribute("blog", new Blog());  //返回一个blog对象给前端th:object
        setTypeAndTag(model);
        return "admin/blogs-input";
    }
    @PostMapping("/blogs") //新增、编辑博客
    public String addBlog(Blog blog, HttpSession session, RedirectAttributes attributes){
        System.out.println("addBlog==========="+blog);
        User user = (User) session.getAttribute("user");
        //设置user属性
        blog.setUser(user);
        //设置用户id
        blog.setUserId(blog.getUser().getId());
        //设置blog的type
        Type type = typeService.getType(blog.getType().getId());
        System.out.println("add内部type接收情况------------"+type);

        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //给blog中的List<Tag>赋值
        //blog.setTags(tagService.getTagByString(blog.getTagIds()));
        String tagIds = blog.getTagIds();
        List<Tag> tagByString = tagService.getTagByString(tagIds);
        blog.setTags(tagByString);

        if (blog.getId() == null) {   //id为空，则为新增
            blogService.saveBlog(blog);
        } else {
            blogService.updateBlog(blog);
        }

        attributes.addFlashAttribute("success", "新增成功");
        return "redirect:/admin/blogs";
    }

    //删除博客
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlogs(@PathVariable Long id, RedirectAttributes attributes){
        System.out.println("删除博客的id是："+id);
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("success", "删除成功");
        return "redirect:/admin/blogs";
    }
    @GetMapping("/blogs/{id}/input") //去编辑博客页面
    public String toEditBlog(@PathVariable Long id, Model model){
        Blog blog = blogService.getById(id);
        //blog.init();   //将tags集合转换为tagIds字符串
        model.addAttribute("blog", blog);     //返回一个blog对象给前端th:object
        setTypeAndTag(model);
        return "admin/blogs-input";
    }
}
