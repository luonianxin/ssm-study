<%--
  Created by IntelliJ IDEA.
  User: lnx
  Date: 20-10-12
  Time: 下午4:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色列表</title>
    <script src="<%= request.getContextPath()%>/static/js/jquery-3.5.1.min.js"></script>
</head>
<body>
<script>
    $(document).ready(function(){
        //此处的ｊｓｏｎ参数必须与类ＲｏｌｅＰａｒａｍ一一对应
        var data　= {
            roleName:'role',
            note:'note',
            pageParam:{
                start:1,
                limit:20
            }
        };

        /*$.ajax({
            url:"<%= request.getContextPath()%>/params/findRoles.do",
            type:"POST",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(data){
                console.log(data);
            }
        });*/
        //使用ｓｐｒｉｎｇｍｖｃ接收列表数据
        //删除角色数组
        var  idList =[4, 5, 6];
        $.ajax({
            url:"<%= request.getContextPath()%>/params/deleteRoles.do",
            type:"POST",
            contentType:"application/json",
            data:JSON.stringify(idList),
            success:function(data){
                console.log(data);
            }
        });
        insertRoles();
    });


    function insertRoles(){
        var roleList = [
            {"roleName":"role_name_1","note":"note_1"},
            {"roleName":"role_name_2","note":"note_2"},
            {"roleName":"role_name_3","note":"note_3"}
        ];
        $.ajax({
            url:"<%= request.getContextPath()%>/params/insertRoles.do",
            type:"POST",
            contentType:"application/json",
            data:JSON.stringify(roleList),
            success:function(data){
                console.log(data);
            }
        });
    }
</script>
</body>
</html>
