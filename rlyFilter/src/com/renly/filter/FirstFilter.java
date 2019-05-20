package com.renly.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
/**
 * @author ${任岚杨}
 * @title: FirstFilter
 * @projectName rlyJavaEE
 * @description: TODO
 * @date 2019/5/2010:14
 */
//@WebFilter(filterName = "log",urlPatterns = "/*")
public class FirstFilter implements Filter {
    private  FilterConfig config;

    /**
     *实现初始化方法
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config=filterConfig;
    }

    /**
     *实现销毁方法
     */
    @Override
    public void destroy() {
        this.config=null;
    }

    /**
     *执行过滤核心方法
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        //------------------------对用户请求预处理-----------------------------------
        //获取ServletContext对象，记录日志
        ServletContext servletContext = this.config.getServletContext();
        long before = System.currentTimeMillis();
        System.out.println("开始过滤");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //输出提示信息
        System.out.println("获取到用户请求的地址"+httpServletRequest.getServletPath());
        //Filter只是链式处理，请求依然放行
        filterChain.doFilter(servletRequest,servletResponse);
        long after = System.currentTimeMillis();
        System.out.println("过滤结束");
        System.out.println("请求并定位到"+httpServletRequest.getRequestURI()+"所花费时间："+(after-before));


    }
}
