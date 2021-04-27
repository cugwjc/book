<%--
  Created by IntelliJ IDEA.
  User: wjc
  Date: 2020/12/20
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span>欢迎<span class="um_span">${ sessionScope.user.username }</span>光临Cuger书城</span>
    <a href="orderServlet?action=showUserOrder">我的订单</a>
    <a href="userServlet?action=logout" id="logout">注销</a>&nbsp;&nbsp;
    <script type="text/javascript">
        $(function () {
            $("#logout").click(function () {
                return confirm("确认注销用户【${sessionScope.user.username}】");
            });
        });
    </script>
    <a href="index.jsp">返回</a>
</div>
