package com.renly.listener;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author ${任岚杨}
 * @title: LoginServlet
 * @projectName rlyJavaEE
 * @description: TODO
 * @date 2019/5/2321:46
 */
public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String errMsg ="";
        RequestDispatcher rd;
        String username = req.getParameter( "username" );
        String password = req.getParameter( "password" );
        ServletContext sc= req.getServletContext();
        Object a = sc.getAttribute( "conn" );


        String sql = "select * from ba_user";

    }


}
