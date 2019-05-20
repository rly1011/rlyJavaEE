package com.renly.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author ${任岚杨}
 * @title: ${NAME}
 * @projectName rlyJavaEE
 * @description:
 * HttpServlet里的三个方法：
 * 1.service(HttpServletRequest req, HttpServletResponse resp)
 * 2.doGet(HttpServletRequest req, HttpServletResponse resp)
 * 3.doPost（HttpServletRequest req, HttpServletResponse res)
 *
 * 在servlet中默认情况下，无论你是get还是post提交过来都会经过service()方法来处理，然后转向到doGet或是doPost方法，
 * 注意，sun只是定义了servlet接口，而实现servlet接口的就是类似于tomcat的服务器
 *
 *
 * @date 2019/5/1710:54
 */
//@WebServlet比XML配置好用的多
@WebServlet(name = "firstServlet",urlPatterns = {"/firstServlet"})
public class FirstServlet extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding( "GBK" );
        res.setContentType( "text/html;charSet=GBK" );
        String name = req.getParameter( "name" );
        String gender = req.getParameter( "gender" );
//      获取页面输出流
        PrintStream out = new PrintStream( res.getOutputStream() );
//      输出HTML页面标签
        out.println(  "<html><head></head><title>测试</title><body>");
        out.println("你的名字"+name+"<hr/>");
        out.println("你的性别"+gender+"<hr/>");
        out.println("</body></html>");



    }
}
