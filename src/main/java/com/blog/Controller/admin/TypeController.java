package com.blog.Controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.Pojo.Type;
import com.blog.Service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")  //后台分类页,查询所有的分类
    public String typesIndex(Model model,
                             @RequestParam(value="pn",defaultValue="1") int pn){
//        List<Type> allType = typeService.getAllType();
//        model.addAttribute("TypeList",allType);
        /*
        * 分页操作
        * */
        Page<Type> typePage = new Page<Type>(pn,2);
        Page<Type> page = typeService.page(typePage, null);

        model.addAttribute("page",page);
        return "admin/types";
    }

    //新增分类标签
    @GetMapping("/types/input")
    public String TypeIn(){
        return "admin/types-input";
    }
    //保存分类
    @PostMapping("/saveTypes")
    public String saveType(Type type, RedirectAttributes redirectAttributes,Model model){
        System.out.println("测试---------------"+type.getName());
        try {
            Type nameType = typeService.getNameType(type.getName());
            System.out.println(nameType+"========");
            if(nameType != null){
                model.addAttribute("msg","不能添加重复的分类");
                return "admin/types-input";
            }else{
                int i = typeService.saveType(type);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/types";
    }
    //进入type的修改，使用type-input
    @GetMapping("/types/{id}/input")
    public String toEditType(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-modify";
    }
    //type修改后的提交功能
    @PostMapping("/types/{id}")
    public String editType(@PathVariable Long id,Type type,Model model){
        try {
            /*System.out.println(type.getName());
            Type nameType = typeService.getType(id);*/
            typeService.updateType(type);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/types";
    }
    @GetMapping("/types/{id}/delete")  //没有定义方法，使用的是mybatis-plus方法
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.removeById(id);
        return "redirect:/admin/types";
    }
}
