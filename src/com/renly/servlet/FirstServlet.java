package com.renly.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author ${任岚杨}
 * @title: ${NAME}
 * @projectName rlyJavaEE
 * @description: TODO
 * @date 2019/5/1710:54
 */
public class FirstServlet extends javax.servlet.http.HttpServlet {
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
