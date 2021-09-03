package com.blog.Controller.admin;

import com.blog.Pojo.Tag;
import com.blog.Pojo.Type;
import com.blog.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private  TagService tagService;


    @GetMapping("/tags")  //进入tags标签
    public String toTags(Model model,
                         @RequestParam(value="pn",defaultValue="1") int pn){
        Page<Tag> tagPage = new Page<>(pn, 2);
        Page<Tag> page = tagService.page(tagPage, null);
        model.addAttribute("page",page);
        return "admin/tags";
    }

    //添加标签--跳转到添加页面
    @GetMapping("/tags/input")
    public String tagInput(){

        return "admin/tag-input";
    }
    @PostMapping("/saveTags")
    public String saveType(Tag tag, RedirectAttributes redirectAttributes, Model model){
        System.out.println(tag.getName()+"---------------");
        try {
            Tag nameTag = tagService.getNameTag(tag.getName());
            if(nameTag != null){
                model.addAttribute("msg","不能添加重复的分类");
                return "admin/tag-input";
            }else{
                int i = tagService.saveTag(tag);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/tags";
    }
    //进入type的修改，使用type-input
    @GetMapping("/tags/{id}/input")
    public String toEditType(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getById(id));
        return "admin/tag-modify";
    }
    //tag修改后的提交功能
    @PostMapping("/tags/{id}")
    public String editType(@PathVariable Long id, Tag tag, Model model){
        try {
            /*System.out.println(type.getName());
            Type nameType = typeService.getType(id);*/
            tagService.updateTag(tag);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/tags";
    }
    @GetMapping("/tags/{id}/delete")  //没有定义方法，使用的是mybatis-plus方法
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.removeById(id);
        return "redirect:/admin/tags";
    }

}
