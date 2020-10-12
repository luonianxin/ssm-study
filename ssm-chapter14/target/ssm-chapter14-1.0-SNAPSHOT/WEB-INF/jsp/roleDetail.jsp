<%--
  Created by IntelliJ IDEA.
  User: lnx
  Date: 20-10-9
  Time: 下午5:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>out标签的使用</title>
</head>
<body>
<div align="center">
<table border="2"　>
    <thead align="center">ｒｏｌｅ信息的完整展示</thead>
    <tr>
        <td>标签</td>
        <td>值</td>
    </tr>
    <tr>
        <td>角色编号</td>
        <td><c:out value="${role.id}"/></td>
    </tr>   
    <tr>
        <td>角色名称</td>
        <td><c:out value="${role.roleName}"/> </td>
    </tr>   
    <tr>
        <td>角色备注</td>
        <td><c:out value="${role.note}"/></td>
    </tr>
</table>
</div>
</body>
</html>
