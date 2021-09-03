package com.blog.Controller.admin;

import com.blog.Pojo.User;
import com.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class UserCheckController {
    @Autowired
    private UserService userService;

    @GetMapping()  //进入登录页
    public String loginPage(){
        return "admin/login";
    }
    @PostMapping("/login")  //实现登录
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes attributes){
        User user = userService.checkUser(username, password);
        if(user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            System.out.println("测试：运行到这了");
            return "admin/index";
        }else{
            attributes.addFlashAttribute("msg","用户名或密码错误");
            return "redirect:/admin";
        }
    }
    //实现登出功能
    @GetMapping("/logout")
    public String logoutPage(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
