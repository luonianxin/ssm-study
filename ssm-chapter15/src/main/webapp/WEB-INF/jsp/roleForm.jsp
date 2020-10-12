<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>测试ＭＶＣ参数传递</title>
    <script src="../src/js/jquery-3.5.1.min.js"></script>
</head>
<body>
<form id="form" action="<%= request.getContextPath()%>/params/requestParam.do" >
    <table>　
        <thead><h2>测试ｍｖｃ参数传递</h2></thead>
        <tr>
            <td>角色名称　</td>
            <td><input name="role_name" id="role_name" value=""></td>
        </tr>
        <tr>
            <td>备注</td>
            <td><input name="note" id="note" value="note"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="提交" align="right"></td>
        </tr>
    </table>
</form>
</body>
</html>
