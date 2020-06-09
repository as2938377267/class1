<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pojo.UserInfo" %><%--
  Created by IntelliJ IDEA.
  User: xiejiaxin
  Date: 2020/6/2
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Map map = (Map) request.getAttribute("map");
    List<UserInfo> list = (List<UserInfo>) map.get("list");
%>

<%for(UserInfo userInfo : list){%>
<%=userInfo.getUsername()%>|<%=userInfo.getPassword()%><br>
<%}%>

当前<%=map.get("page")%>/<%=map.get("pagecount")%>页
<a href="user.do?p=fenye&page=1&size=<%=map.get("size")%>">首页</a>
<a href="user.do?p=fenye&page=<%=(Integer)map.get("page")-1%>&size=<%=map.get("size")%>">下一页</a>
<a href="user.do?p=fenye&page=<%=(Integer)map.get("page")+1%>&size=<%=map.get("size")%>">上一页</a>
<a href="user.do?p=fenye&page=<%=map.get("pagecount")%>&size=<%=map.get("size")%>">尾页</a>

</body>
</html>
