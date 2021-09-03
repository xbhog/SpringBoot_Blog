package com.blog.Config;

import com.blog.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//将自定义的拦截器添加到SpringBoot的web功能中
@Configuration
public class WebLoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")  //添加的需要过滤路径
                //出去不需要过滤的路径，包括静态资源
                .excludePathPatterns("/","/blog/**","/tags/**","/types/**","/archives/**","/about/**","/admin","/admin/login","/css/**","/images/**","/js/**","/lib/**");
    }
}
