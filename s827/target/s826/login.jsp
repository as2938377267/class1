<%--
  Created by IntelliJ IDEA.
  User: xiejiaxin
  Date: 2020/6/8
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form method="post" action="user.do?p=login">
        用户名：<input type="text" name="username"><br>
        用户名：<input type="password" name="password"><br>
        <input type="submit" value="登录">
    </form>
</body>
</html>
