
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>SpringMVC　国际化</title>
</head>
<body>
    <h2>找到属性文件变量名为welcome的配置</h2>
    <h2><spring:message code="welcome"></spring:message></h2>
    Locale:${pageContext.response.locale}
</body>
</html>
