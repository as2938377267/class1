<%@ page import="java.util.List" %>
<%@ page import="com.pojo.Userinfo" %><%--
  Created by IntelliJ IDEA.
  User: xiejiaxin
  Date: 2020/6/1
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List<Userinfo> list = (List<Userinfo>) request.getAttribute("list");
%>

<%for(Userinfo userInfo : list){%>
<%=userInfo.getUsername()%>|<%=userInfo.getPassword()%><a href="user.do?p=del&username=<%=userInfo.getUsername()%>">删除</a><br>
<%}%>

</body>
</html>
