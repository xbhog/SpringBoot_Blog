package com.blog.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.Pojo.Blog;
import com.blog.Pojo.Tag;
import com.blog.Pojo.Type;
import com.blog.Service.BlogService;
import com.blog.Service.TagService;
import com.blog.Service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {
    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;
    @GetMapping("/tags/{id}")
    public String toShowTag(@PathVariable Long id, @RequestParam(value="pn",defaultValue = "1") int pn, Model model){
        List<Tag> allTag = tagService.getAllTag();
        if(id == -1){
            id = allTag.get(0).getId();
        }
        Page<Blog> blogPage = new Page<>(pn, 1000);

        IPage<Blog> byTagId = blogService.getByTagId(blogPage, id);
        model.addAttribute("tags",allTag);
        model.addAttribute("pageInfo",byTagId);
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
