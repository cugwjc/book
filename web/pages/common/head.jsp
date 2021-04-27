<%--
  Created by IntelliJ IDEA.
  User: wjc
  Date: 2020/12/20
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
    + request.getContextPath() + "/";
%>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style4.11.css">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
