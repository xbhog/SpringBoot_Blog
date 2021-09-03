package com.blog.Handler;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);


    /*ModelAndView其实就是两个作用，一个是指定返回页面，另一个是在返回页面的同时添加属性*/
    @ExceptionHandler(Exception.class)  // 表示 捕获 全部异常
    public ModelAndView handleException(HttpServletRequest request, Exception e) throws Exception{
        //获取异常的信息
        logger.error(() -> {
            return String.format("Request URL : %s,Exception : %s ", request.getRequestURL(),e);
        },e);
        //获取异常类的相应信息
        System.out.println("我进来了--------");
        /*ResponseStatus annotation = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
        String code = annotation.code().toString().split(" ")[0];
        //如果是404，那么交给springboot自动管理
        System.out.println("查看发送的状态码是："+code);
        if(code.equals("404")){
            throw e;
        }*/
        /*不是很明白*/
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url",request.getRequestURL());
        modelAndView.addObject("exception",e);
        //返回给error页面
        modelAndView.setViewName("error/error");
        return modelAndView;
    }
}
