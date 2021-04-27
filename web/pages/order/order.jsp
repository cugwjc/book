<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $(".signOrder").click(function () {
                return confirm("是否确认收货？");
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">我的订单</span>
    <%@include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <table>

        <tr>
            <c:if test="${empty sessionScope.userOrders}">
                <td colspan="4">当前无订单</td>
            </c:if>
            <c:if test="${not empty sessionScope.userOrders}">
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
            <td>详情</td>
            </c:if>
        </tr>
        <c:forEach items="${sessionScope.userOrders}" var="userOrder">
        <tr>
            <td>${userOrder.createTime}</td>
            <td>${userOrder.price}</td>
            <td>
                <c:if test="${userOrder.status == 0}">
                    未发货
                </c:if>
                <c:if test="${userOrder.status == 1}">
                    已发货
                    <br>
                    <a class="signOrder" href="orderServlet?action=signOrder&orderId=${userOrder.orderId}">确认收货</a>
                </c:if>
                <c:if test="${userOrder.status == 2}">
                    已签收
                </c:if>

            </td>
            <td><a href="orderServlet?action=orderDetail&orderId=${userOrder.orderId}">查看详情</a></td>
        </tr>
        </c:forEach>
    </table>


</div>

<%@include file="/pages/common/footer.jsp" %>
</body>
</html>