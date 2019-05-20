package com.renly.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author ${任岚杨}
 * @title: LoginFilter
 * @projectName rlyJavaEE
 * @description: TODO
 * @date 2019/5/2010:25
 */
@WebFilter(filterName = "login"
        ,urlPatterns = ("/*")
        ,initParams = {
            @WebInitParam(name="encoding",value = "utf-8")
            ,@WebInitParam(name="loginPage",value = "login.jsp")
})
public class LoginFilter implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config=filterConfig;
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        //获取Filter配置参数
        String encoding = config.getInitParameter("encoding");
        String loginPage = config.getInitParameter("loginPage");
        //设置request编码集
        servletRequest.setCharacterEncoding(encoding);

        /*
        验证用户是否登录，如果没有登录，系统跳转到到登录页面
         */
        //获取用户session,然后获取用户请求页面
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpServletRequest.getSession();
        String requestPath= httpServletRequest.getServletPath();
        //如果用户请求的页面在登录页面和处理登录页面纸外，
        // session范围内的user为null，则表明用户未登录，未登录跳转到登录页面
        if (httpSession.getAttribute("user")==null
                &&!(requestPath.endsWith(loginPage))){
            //转发到登录页面
            httpServletRequest.setAttribute("tip","您未登录，不能直接访问系统！");
            httpServletRequest.getRequestDispatcher(loginPage).forward(servletRequest,servletResponse);
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }



    }

    @Override
    public void destroy() {
        config=null;
    }
}