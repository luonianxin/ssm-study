<%--
  Created by IntelliJ IDEA.
  User: lnx
  Date: 20-9-30
  Time: 上午9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 align="center">
    hello spring mvc
</h1>
<%
    request.setAttribute("id",100L);
    request.getRequestDispatcher("/attributes/requestAttributes.do")
            .forward(request,response);
%>
</body>
</html>
