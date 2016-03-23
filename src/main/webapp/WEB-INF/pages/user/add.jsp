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
    <title>用户增加</title>
</head>
<body>
<form method="post" enctype="multipart/form-data">
    <p>username: <input name="username"/></p>
    <p>password: <input name="password" type="password"/></p>
    <p>attach:<input name="attach" type="file"/></p>
    <input type="submit" name="提交"/>
</form>

</body>
</html>
