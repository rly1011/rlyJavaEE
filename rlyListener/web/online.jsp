<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<html>
<head>
    <title>用户在线信息</title>
</head>
<body>
    在线用户：
    <table width="400" border="1">
    <%
        Map online = (Map) application.getAttribute( "online" );
        for (Object sessionId : online.keySet())
    {%>
    <tr>
        <td><%=sessionId%>
        <td><%=online.get(sessionId.toString())%>
    </tr>
    <%}%>
    </table>
</body>
</html>
