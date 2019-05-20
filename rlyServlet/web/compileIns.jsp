<%--
JSP三个编译指令：通知JSP引擎的消息，不直接生成输出，将JSP编译成Servlet起作用

-page指令
功能：设定整个JSP网页的静态属性。
语法：<%@ page 标签元素=“值”%>,比如 <%@ page language=“java”%>
标签元素：language、import、contentType、session、errorPage、isErrorPage等等。

-include指令
功能：include指令用来向当前页面插入一个静态文件的内容。这个文件可以是JSP、HTML、文本或是Java程序。
语法：<%@ include file=“filename” %> 比如 <%@ include file=“111.txt” %>
标签元素：file

-taglib指令
功能：使用标签库定义新的自定义标签，在JSP页面中启用定制行为。
当页面引用了用户自定义标签时，taglib指令用于引用自定义标签库，并指定标签的前缀。
语法：<%@ taglib uri="URIToTagLibrary" prefix="tagPrefix" %>
标签元素：uri、Prefixpage指令元素的属性
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP三个编译指令</title>
</head>
<body>

</body>
</html>
