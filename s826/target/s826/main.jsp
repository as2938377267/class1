
<%@ page import="userinfo.Userinfo" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach items="${map.list}" var="userinfo">
    ${userinfo.username}${userinfo.password}<br>
</c:forEach>

<a href="user.do?fenye&page=1&size=${map.size}>">首页</a>
<a href="user.do?fenye&page=${map.page-1}&size=${map.size}">上一页</a>
<a href="user.do?fenye&page=${map.page+1}&size=${map.size}">下一页</a>
<a href="user.do?fenye&page=${map.pageCount}&size=${map.size}">尾页</a>


</body>
</html>
