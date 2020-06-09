<%--
  Created by IntelliJ IDEA.
  User: xiejiaxin
  Date: 2020/6/4
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${goods.goodsid}
${goods.goodsname}
${goods.goodscount  }
${goods.goodsprice}
<img src="img/${goods.goodspic}">

<c:forEach items="${goods.set}"  var="pic">
    <img src="img/${pic.pname}" style="height: 300px;width: 500px">
</c:forEach>

<input type="text" value="1" id="num">
<a href="javascript:void(0)" onclick="addCar()">加入购物车</a>
<a href="javascript:void(0)" onclick="shoucang()">收藏</a>
</body>
<script>
    function addCar() {
        var num = document.getElementById("num").value;
        location = "goods.do?p=addCar&num="+num+"&goodsid=${goods.goodsid}";
    }
    function shoucang() {
        location = "user.do?p=shoucang&goodsid=${goods.goodsid}";
    }
</script>
</html>
