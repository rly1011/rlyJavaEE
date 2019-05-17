<%--
- JSP本质是Servlet，当用户向指定的Servlet发送请求时，Servlet利用输出流动态的html页面，
jsp其实也是servlet的一种简化，使用jsp时，其实还是使用了servlet，因为web应用中的
每个jsp页面都会由servlet容器生成对应的servlet。

- jsp页面的内容由两部分组成：
1.静态部分：标准的HTML标签，静态的页面内容，这些内容与静态的HTML页面相同。
2.动态部分：受Java程序控制的内容，这些内容由Java脚本动态生成。

最简单的理解： Servlet 是在Java中使用HTML的技术；JSP是在HTML中使用Java的技术
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP基本原理</title>
</head>
<body>
<%out.println(new java.util.Date(  ) );%>
</body>
</html>
