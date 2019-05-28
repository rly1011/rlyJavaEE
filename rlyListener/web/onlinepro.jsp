<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: rly10
  Date: 2019/5/21
  Time: 16:04
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户在线信息</title>
</head>
<body>
    <h1>当前在线用户</h1>
    <table width="640" border="1">
        <%
              // 第一步：定义数据驱动名称和url路径
              String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
              String DB_URL = "jdbc:mysql://localhost/rlyjavaee?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
              // 第二步：定义数据库用户名和密码
              String USER = "root";
              String PASS = "zxcvbnm2019";
              Connection conn = null;
              Class.forName(JDBC_DRIVER);
              conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
              String sql = "select * from online_inf ";
              PreparedStatement pstmt;
              pstmt = (PreparedStatement)conn.prepareStatement(sql);
              ResultSet rs = pstmt.executeQuery();

              while (rs.next())
              {%>
        <tr>
            <td><%=rs.getString(1)%></td>
            <td><%=rs.getString(2)%></td>
            <td><%=rs.getString(3)%></td>
            <td><%=rs.getString(4)%></td>
            <td><%=rs.getString(5)%></td>
        </tr>
        <%}%>
        <%  pstmt.close();
            conn.close();%>
    </table>
</body>
</html>
