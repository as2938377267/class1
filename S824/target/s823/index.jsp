<html>
<body>
<%
    if(session.getAttribute("userinfo")==null){
        response.sendRedirect("regist.jsp");
    }
%>
</body>
</html>
