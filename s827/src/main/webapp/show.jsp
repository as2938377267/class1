<%--
  Created by IntelliJ IDEA.
  User: xiejiaxin
  Date: 2020/6/4
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    #bigDiv {
        width: 1000px;
        height: 1000px;
        border: 1px solid blue;
        text-align: center;
        margin: auto;
    }

    .smalldiv {
        width: 250px;
        height: 250px;
        border: 1px solid red;
        float: left;
        margin-left: 15px;
        margin-top: 10px;

    }

    .goodspic {
        width: 200px;
        height: 200px;

    }

</style>
<body>
<div id="bigDiv">
    <c:forEach items="${map.list}" var="goods">
        <div class="smalldiv">
                ${goods.goodsid}${goods.goodsname}
            <a href="goods.do?p=findbyid&goodsid=${goods.goodsid}" title="查看详情"><img class="goodspic" src="img/${goods.goodspic}"></a>
        </div>
    </c:forEach>
    当前${map.page}/${map.pagecount}页
    <a href="goods.do?p=fenye&page=1&size=${map.size}">首页</a>
    <a href="goods.do?p=fenye&page=${map.page-1}&size=${map.size}">上一页</a>
    <a href="goods.do?p=fenye&page=${map.page+1}&size=${map.size}">下一页</a>
    <a href="goods.do?p=fenye&page=${map.pagecount}&size=${map.size}">尾页</a>
</div>
</body>
</html>
