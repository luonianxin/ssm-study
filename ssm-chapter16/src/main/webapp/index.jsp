
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TestConverterController</title>
</head>
<body>
<h1 align="center">
    hello spring mvc
</h1>
<form id="form" action="<%= request.getContextPath()%>/converter/format.do">
    <table>
        <tr>
            <td>日期</td>
            <td><input id="date" name="date1" value="2020-10-20"/></td>
        </tr>
        <tr>
            <td>金额数字</td>
            <td><input id="amount" name="amount1" value="123,00,22"/></td>
        </tr>
        <tr>
            <td align="right"><input type="submit" value="提交"/></td>
        </tr>
    </table>
</form>
</body>
</html>
