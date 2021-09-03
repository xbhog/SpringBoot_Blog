package com.blog.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.NotFoundException;
import com.blog.Pojo.Blog;
import com.blog.Pojo.Tag;
import com.blog.Pojo.Type;
import com.blog.Service.BlogService;
import com.blog.Service.TagService;
import com.blog.Service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;
    @GetMapping("/")
    public String index(@RequestParam(value="pn",defaultValue = "1") int pn, Model model){
        //显示博客
        Page<Blog> blogPage = new Page<>(pn, 10);
        IPage<Blog> indexBlog = blogService.getIndexBlog(blogPage);
        model.addAttribute("page",indexBlog);
        //显示类型
        List<Type> blogType = typeService.getBlogType();  //联表查询
        model.addAttribute("types",blogType);
        //显示标签
        List<Tag> blogTag = tagService.getBlogTag();
        model.addAttribute("tags",blogTag);
        //前端显示推荐
        List<Blog> recommendBlog = blogService.getAllRecommendBlog();
        model.addAttribute("recommendBlogs",recommendBlog);
        return "index";
    }
    @PostMapping("/search")
    public String search(@RequestParam(value="pn",defaultValue = "1") int pn,
                         Model mode, @RequestParam("query") String query
                         ){
        Page<Blog> blogPage = new Page<>(pn, 2);
        IPage<Blog> blogIPage = blogService.getsearchBlog(blogPage, query);
        mode.addAttribute("pageInfo",blogIPage);
        mode.addAttribute("query",query);
        return "search";
    }
    //博客详情
    @GetMapping("/blog/{id}")
    public String getDatailBlog(@PathVariable Long id, Model model){
        Blog detailBlog = blogService.getDetailBlog(id);
        String avatar = detailBlog.getUser().getAvatar();
        System.out.println("user-----"+avatar);
        model.addAttribute("blog",detailBlog);
        model.addAttribute("avatar",avatar);
        return "blog";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

}
