package com.renly.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * @author ${任岚杨}
 * @title: LoginServlet
 * @projectName rlyJavaEE
 * @description: 利用Servlet做控制器做个登录页面控制
 * @date 2019/5/1712:43
 */
@WebServlet(name = "login",urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String errMsg ="";
        RequestDispatcher rd;
        String username = req.getParameter( "username" );
        String password = req.getParameter( "password" );

        //匹配用户名和密码
        if ("rly".equals( username )&&"123".equals( password )){
            //获取session对象
            HttpSession httpSession = req.getSession();
            //设置Session属性，跟踪用户会话状态
            httpSession.setAttribute( "name",username );
            //获取转发对象
            rd = req.getRequestDispatcher( "/index.jsp" );
            //转发请求
            rd.forward( req,res );
        }else {
            errMsg+="用户名"+username+"的用户名和密码错误，请重新输入";
        }

        //用户名或者密码错误，重定向到登录界面
        if (errMsg!=null && !errMsg.equals( "" )){

            rd=req.getRequestDispatcher( "/login.jsp" );
            req.setAttribute( "err",errMsg );
            rd.forward( req,res );
        }

    }
}
