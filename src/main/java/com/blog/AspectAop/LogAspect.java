package com.blog.AspectAop;

import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Aspect  //声明一个切面
@Component  //将该类加入到容器中
public class LogAspect {
    /*注意在slf4j包下的*/
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 定义切点
     * 通过doBefore  doAfter实现处理拦截的信息
     */
    @Pointcut("execution(* com.blog.Controller..*.*(..))")
    public void log(){}

    /*在进入controller之前处理流*/
    @Before("log()")
    //JoinPoint对象封装了SpringAop中切面方法的信息,在切面方法中添加JoinPoint参数,就可以获取到封装了该方法信息的JoinPoint对象
    public void doBefore(JoinPoint joinPoint){
        System.out.println("在进入controller之前处理流-------------");
        /**
         * 获取传入相关信息
         *   请求 url
         *   访问者 ip
         *   调用方法 classMethod
         *   参数 args
         *   返回内容
         */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();
        //获取封装了署名信息的对象,在该对象中可以获取到目标方法名,所属类的Class等信息
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+","+joinPoint.getSignature().getName();
        // 1. 获取URL
        String url = request.getRequestURL().toString();
        //2. 获取ip地址
        String addr = request.getRemoteAddr();

        /*创建一个类RequestData，来保存相关信息*/
        RequestData requestData = new RequestData(
                url, addr, classMethod, joinPoint.getArgs()
        );
        //在控制台打印出来
        logger.info("RequestData------{}",requestData);

    }
    /*在进入controller之后处理流*/
    @After("log()")
    public void doAfter(){
        System.out.println("在进入controller之后处理流-------------");
    }

    @AfterReturning(returning = "result",pointcut="log()")
    public void doAfterReturning(Object result){
        logger.info("Return ------ {}",result );
    }
    private class RequestData{
        private String url;
        private String ipAddr;
        private String classMethod;  //获取使用的类的方法
        private Object[] args;  //获取传入的属性

        public RequestData(String url, String ipAddr, String classMethod, Object[] args) {
            this.url = url;
            this.ipAddr = ipAddr;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestData{" +
                    "url='" + url + '\'' +
                    ", ipAddr='" + ipAddr + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}
