package com.blog.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.Dao.TypeDao;
import com.blog.Pojo.Blog;
import com.blog.Pojo.Type;
import com.blog.Service.BlogService;
import com.blog.Service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;


    @GetMapping("/types/{id}")
    public String toShowType(@PathVariable Long id, @RequestParam(value="pn",defaultValue = "1") int pn, Model model){
        List<Type> allType = typeService.getAllType();
        if(id == -1){
            id = allType.get(0).getId();
        }
        Page<Blog> blogPage = new Page<>(pn, 1000);

        IPage<Blog> byTypeId = blogService.getByTypeId(blogPage, id);
        model.addAttribute("types",allType);
        model.addAttribute("pageInfo",byTypeId);
        model.addAttribute("activeTypeId",id);
        return "types";
    }

}
