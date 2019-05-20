<%--
  Created by IntelliJ IDEA.
  User: rly10
  Date: 2019/5/17
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<span style="color: red;font-weight: bold">
    <%if (request.getAttribute( "err" )!=null){
        out.println(request.getAttribute( "err")+"<br/>" );
    }%>
</span>
<h1>请输入用户名与密码</h1>
<form id="login" method="post" action="login">
    用户名：<input type="text" name="username" />
    密码：<input type="password" name="password">
    <input type="submit" value="登录">
</form>

</body>
</html>
