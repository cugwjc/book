<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $(".cart").click(function () {
                var bookId = $(this).attr("bookId");
                $.getJSON("${pageScope.basePath}cartServlet","action=ajaxAddItem&id=" + bookId,function (data) {
                   $("#cartCount").text("您的购物车中有" + data.count + "件商品");
                   $("#cartBookName").text(data.bookname);
                });
                if(${ empty sessionScope.cart.items }){

                }
            });
            $(".nocart").click(function () {
                if(confirm("尚未登录，请登录账号！")){
                    location.href = "${pageScope.basePath}pages/user/login.jsp";
                }else{
                    return false;
                }
            });
            $(".outcart").click(function () {
                if(confirm("尚未登录，请登录账号！")){
                    location.href = "${pageScope.basePath}pages/user/login.jsp";
                }else{
                    return false;
                }
            });
        });
    </script>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <div>
        <c:if test="${ empty sessionScope.user }">
        <a href="pages/user/login.jsp">登录</a> |
        <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <c:if test="${ not empty sessionScope.user }">
            <span>欢迎<span class="um_span">${ sessionScope.user.username }</span>光临Cuger书城</span>
            <a href="pages/order/order.jsp">我的订单</a>
            <a href="userServlet?action=logout" id="logout">注销</a>&nbsp;&nbsp;
            <script type="text/javascript">
                $(function () {
                    $("#logout").click(function () {
                        return confirm("确认注销用户【${sessionScope.user.username}】");
                    });
                });
            </script>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
        <a  href="pages/cart/cart.jsp">购物车</a>
        </c:if>
        <c:if test="${empty sessionScope.user}">
            <a class="outcart" href="pages/user/login.jsp">购物车</a>
        </c:if>
        <c:if test="${ sessionScope.user.username == 'admin'}">
        <a href="pages/manager/manager.jsp">后台管理</a>
        </c:if>
    </div>
</div>

<div id="main">
    <div id="book">
        <div class="book_cond">

        </div>
        <div class="book_cond">
            <form action="client/clientBookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>
        <div class="book_cond1">
            <form action="client/clientBookServlet" method="get">
                <input type="hidden" name="action" value="pageByName">
                书名：<input id="bname" type="text" name="bname" value="${param.bname}">
                <input type="submit" value="查询"/>
            </form>
        </div>
        <div style="text-align: center" >
            <c:if test="${ not empty sessionScope.cart.items }">
            <span id="cartCount">您的购物车中有${ sessionScope.cart.totalCount }件商品</span>
            <div>
                您刚刚将<span id="cartBookName" style="color: red">${ sessionScope.bookname }</span>加入到了购物车中
            </div>
            </c:if>
            <c:if test="${ empty sessionScope.cart.items }">
                <span id="cartCount"></span>
                <div>
                    <span id="cartBookName" style="color: red">当前购物车为空！</span>
                </div>
            </c:if>
        </div>
        <c:forEach items="${ requestScope.page.items }" var="book">
        <div class="b_list">
            <div class="img_div">
                <img class="book_img" alt="" src="static/img/default.jpg"/>
            </div>
            <div class="book_info">
                <div class="book_name">
                    <span class="sp1">书名:</span>
                    <span class="sp2">${ book.name }</span>
                </div>
                <div class="book_author">
                    <span class="sp1">作者:</span>
                    <span class="sp2">${ book.author }</span>
                </div>
                <div class="book_price">
                    <span class="sp1">价格:</span>
                    <span class="sp2">￥${ book.price }</span>
                </div>
                <div class="book_sales">
                    <span class="sp1">销量:</span>
                    <span class="sp2">${ book.sales }</span>
                </div>
                <div class="book_amount">
                    <span class="sp1">库存:</span>
                    <span class="sp2">${ book.stock }</span>
                </div>
                <c:if test="${empty sessionScope.user}">
                <div class="book_add">
                    <button bookId = ${book.id} class="nocart">加入购物车</button>
                </div>
                </c:if>
                <c:if test="${not empty sessionScope.user}">
                <div class="book_add">
                    <button bookId = ${book.id} class="cart">加入购物车</button>
                </div>
                </c:if>
        </div>
    </div>
        </c:forEach>
    </div>
        <%@include file="/pages/common/page_nav.jsp"%>
</div>
<div class="video">
    <video src="static/video/bg-index.mp4" muted loop autoplay></video>
</div>

<%@include file="/pages/common/footer.jsp" %>
</body>
</html>