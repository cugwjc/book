<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("#count").change(function () {
                var name = $(this).parent().parent().find("td:first").text();
                var bookId = $(this).attr("bookId");
                if(confirm("是否将《" + name + "》数量改为：" + this.value)){
                    location.href = "${pageScope.basePath}cartServlet?action=updateCount&id=" + bookId + "&count=" + this.value;
                }else{
                    this.value = this.defaultValue;
                }
            });
            $("#clear").click(function () {
               return confirm("真的要清空购物车吗？");
            });
            $(".deleteitem").click(function () {
                var name = $(this).parent().parent().find("td:first").text();
                return confirm("是否删除《" + name + "》?");
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%@include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <c:if test="${ empty sessionScope.cart.items }">
        <tr>
            <td colspan="5">
                <a href="index.jsp">购物车还没有内容，去书城逛一逛吧！</a>
            </td>
        </tr>
        </c:if>
        <c:if test="${ not empty sessionScope.cart.items }">

        <c:forEach items="${sessionScope.cart.items}" var="item">
        <tr>
            <td>${item.value.name}</td>
            <td>
                <input type="text" style="width: 50px;" id="count" bookId="${item.value.id}" value="${item.value.count}">
            </td>
            <td>${item.value.price}</td>
            <td>${item.value.totalPrice}</td>
            <td><a class="deleteitem" href="cartServlet?action=delete&id=${item.value.id}">删除</a></td>
        </tr>
        </c:forEach>
        </c:if>
    </table>
    <c:if test="${ not empty sessionScope.cart.items }">
    <div class="cart_info">
        <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
        <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
        <span class="cart_span"><a id="clear" href="cartServlet?action=clear">清空购物车</a></span>
        <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
    </div>
    </c:if>
</div>

<%@include file="/pages/common/footer.jsp" %>
</body>
</html>