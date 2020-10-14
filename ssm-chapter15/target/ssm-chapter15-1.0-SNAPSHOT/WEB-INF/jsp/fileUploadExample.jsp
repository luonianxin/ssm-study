
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传案例</title>
</head>
<body>
<form action="<%= request.getContextPath()%>/file/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="file" value="请选择需要上传的文件"/>
    <input type="submit" value="上传"/>
</form>
</body>
</html>
