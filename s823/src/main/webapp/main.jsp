<%--
  Created by IntelliJ IDEA.
  User: xiejiaxin
  Date: 2020/5/29
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>矮子望天</title>
</head>
<body>

<%
    if (session.getAttribute("username")==null){
        response.sendRedirect("logist.jsp");
    }

%>

除凶去妖

</body>
</html>
