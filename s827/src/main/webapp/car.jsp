<%--
  Created by IntelliJ IDEA.
  User: xiejiaxin
  Date: 2020/6/5
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${map}" var="m">
    ${m.key.goodsid}|${m.key.goodsname}|<a href="goods.do?p=jian&goodsid=${m.key.goodsid}" >-</a> ${m.value} <a href="goods.do?p=addnum&goodsid=${m.key.goodsid}">+</a> <a href="goods.do?p=deletcar&goodsid=${m.key.goodsid}">删除</a> <br>


</c:forEach>
</body>
</html>
