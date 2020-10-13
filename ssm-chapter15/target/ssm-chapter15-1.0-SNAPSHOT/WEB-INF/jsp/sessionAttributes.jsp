<%@ page import="com.learn.ssm.chapter15.pojo.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>session验证注解的有效性</title>
</head>
<body>
    <%
        Role role = (Role) session.getAttribute("role");
        response.getWriter().println("id:===> "+ role.getId());

        response.getWriter().println("roleName :===> "+ role.getRoleName()+"<br/>");
        response.getWriter().println("note :===> "+ role.getNote()+"<br/>");
        response.getWriter().println("createTime :===> "+ role.getCreateDate()+"<br/>");
        response.getWriter().println("<===================================>"+"<br/>");
        response.getWriter().println("存储的ｉｄ值为：　"+session.getAttribute("id")+"<br/>");

    %>
</body>
</html>
