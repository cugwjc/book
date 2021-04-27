<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cuger会员注册页面</title>
    <%@include file="/pages/common/head.jsp"%>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("#sub_btn").click(function () {
                var str1 = $("#username").val();
                var patt1 = /^\w{5,12}$/;
                if(!patt1.test(str1)){
                    $("span.errorMsg").text("用户名不合法");
                    return false;
                }
                var str2 = $("#password").val();
                var patt2 = /^(?=.*\d)(?=.*[a-zA-Z]).{6,18}$/;
                if(!patt2.test(str2)){
                    $("span.errorMsg").text("密码不合法");
                    return false;
                }
                var str3 = $("#repwd").val();
                if(str2 != str3){
                    $("span.errorMsg").text("两次密码不同");
                    return false;
                }
                var str4 = $("#email").val();
                var patt4 = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                if(!patt4.test(str4)){
                    $("span.errorMsg").text("邮箱不合法");
                    return false;
                }
                var str5 = $.trim($("#code").val());
                if(str5 == null || str5 == ""){
                    $("span.errorMsg").text("验证码为空");
                    return false;
                }
                $("span.errorMsg").text("");

            });
            $("#code_img").click(function () {
                this.src = "${basePath}kaptcha.jpg?d=" + new Date();
            });
            $("#username").blur(function () {
                var username = this.value;
                $.getJSON("${pageScope.basePath}userServlet","action=ajaxExistUsername&username=" + username,function (data) {
                   if(data.existUsername){
                       $("span.errorMsg").text("用户名已存在！");
                   }
                });
            });
        });
    </script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册Cuger会员</h1>
                    <span class="errorMsg">${ requestScope.msg }</span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="regist">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username" value="${ requestScope.username }"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                               name="email" id="email" value="${ requestScope.email }"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 100px;" name="code" id="code"/>
                        <img alt="" src="kaptcha.jpg" id="code_img" style="float: right; margin-right: 50px;width: 110px; height: 40px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>