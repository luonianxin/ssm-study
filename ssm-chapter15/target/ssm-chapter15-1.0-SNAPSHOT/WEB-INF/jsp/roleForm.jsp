<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>测试ＭＶＣ参数传递</title>
    <script src="<%= request.getContextPath()%>/static/js/jquery-3.5.1.min.js"></script>
</head>
<body>
<!-- form serialize() test -->
<form id="form2">
    <table>　
        <thead><h2>测试ｍｖｃ参数传递</h2></thead>
        <tr>
            <td>角色名称　</td>
            <td><input name="roleName" id="roleName"></td>
        </tr>
        <tr>
            <td>备注</td>
            <td><input name="note" id="note"></td>
        </tr>
        <tr>
            <td></td>
            <td><input id= "commit" type="submit" value="提交" align="right"></td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    $(document).ready(function(){
        $("#commit").click(function(){
            var str = $('#form2').serialize();
            console.log("form data"+str);
            debugger;
            $.ajax({
                url:"<%= request.getContextPath()%>/params/commonPojo2.do",
                type:"post",
                data:$('#form2').serialize(),

                success:function(data){

                }
            });
        });
    });
</script>
</body>
</html>
