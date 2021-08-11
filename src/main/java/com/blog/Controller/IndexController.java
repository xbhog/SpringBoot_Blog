package com.blog.Controller;

import com.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /**
     * 访问页面，如果页面为null,则进入自定义的报错流程
     * @return
     */
    @GetMapping("/")
    public String index(){
        String blog = null;
        if(blog == null){
            throw new NotFoundException("博客找不到，请联系管理员"); //自定义类
        }
        return "index";
    }
}
