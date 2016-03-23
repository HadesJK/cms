<%--
  Created by IntelliJ IDEA.
  User: jql
  Date: 15/6/29
  Time: 下午9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
</head>
<body>
<form method="post">
    <p>username: <input name="username" value="${user.username}"/></p>
    <p>password: <input name="password" value="${user.password}"/></p>
    <input type="submit" name="修改"/>
</form>

</body>
</html>
