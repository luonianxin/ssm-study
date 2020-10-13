
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试ｈｉｂｅｒｎａｔｅ表单验证器</title>
</head>
<body>

    <form id="form" action="<%= request.getContextPath()%>/validate/validator.do">
        <table border="1" bgcolor="#a9a9a9">
            <tr>
                <td>产品编号</td>
                <td><input id="productId" name="productId"></td>
            </tr>
            <tr>
                <td>用户编号</td>
                <td><input id="userId" name="userId"></td>
            </tr>
            <tr>
                <td>交易日期</td>
                <td><input id="date" name="date"></td>
            </tr>
            <tr>
                <td>交易价格</td>
                <td><input id="price" name="price"/></td>
            </tr>
            <tr>
                <td>交易数量</td>
                <td><input id="quantity" name="quantity"></td>
            </tr>
            <tr>
                <td>交易总额</td>
                <td><input id="amount" name="amount"></td>
            </tr>
            <tr>
                <td>邮箱</td>
                <td><input id="email" name="email"></td>
            </tr>
            <tr>
                <td>备注</td>
                <td><textarea id="note" name="note" type="text" cols="20" rows="5"></textarea></td>
            </tr>
            <tr>
                <td><input id="commit" type="submit" value="提交" align="right"/></td>
            </tr>
        </table>
    </form>
</body>
</html>
