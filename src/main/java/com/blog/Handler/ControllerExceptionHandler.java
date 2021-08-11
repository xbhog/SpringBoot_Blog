package com.blog.Handler;



import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)  // 表示 捕获 全部异常
    /*ModelAndView其实就是两个作用，一个是指定返回页面，另一个是在返回页面的同时添加属性*/
    public ModelAndView handleException(HttpServletRequest request, Exception e, Model model) throws Exception{
//        String format = String.format("Request URL : {} , Exception : {}", request.getRequestURI(), e);
        /*Supplier<String> callable = () -> {
            return String.format("Request URL : %s ", request.getRequestURL());
        };*/
//        System.out.println(callable.get()+"--------");
        logger.error(() -> {
            return String.format("Request URL : %s,Exception : %s ", request.getRequestURL(),e);
        },e);
        ResponseStatus annotation = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
//        System.out.println(annotation+"=======");
        String code = annotation.code().toString().split(" ")[0];
        if(code.equals("404")){
            throw e;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url",request.getRequestURL());
        modelAndView.addObject("exception",e);
        model.addAttribute("url",request.getRequestURL());
        model.addAttribute("exception",e);
        //返回给error页面
        modelAndView.setViewName("error/error");
        return modelAndView;
    }
}
