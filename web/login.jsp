<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <style type="text/css">
        * {
            margin: 0px;
            padding: 0px;
        }

        .box {
            width: 100%;
            height: 100%;
            background-image: url(img/登录页面.jpg);
            background-repeat: no-repeat;
            background-size: 100% 100%;
        }

        .content {
            border-radius: 20px;
            width: 30%;
            height: 40%;
            background-color: #F1F1F3;
            opacity: 0.9;
            position: relative;
            left: 60%;
            top: 20%;
        }

        .th1 {
            padding-top: 10px;
            margin-left: 50px;
            margin-right: 20px;
        }

        .th2 {
            width: 300px;
            height: 30px;
            border: 1px white solid;
            border-radius: 4px;
            padding-left: 10px;
        }

        .th1,
        .th2 {
            margin-top: 30px;
            margin-bottom: 30px;
        }

        .th3 {
            color: #F0F8FF;
            margin: 30px 50px;
            width: 380px;
            height: 50px;
            border-radius: 20px;
            border-color: #F1F1F3;
            background-color: blue;
            opacity: 0.4;
        }
        .ck{

        }
    </style>
</head>
<body>
<%
    String account = "";
    String password = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("acccok")) {
                account = cookie.getValue();
            }
            if (cookie.getName().equals("pwdcok")) {
                password = cookie.getValue();
            }
        }
    }
%>
<div class="box">
    <div class="content">
        <form action="${path}/login" method="post">
            <div>
                <label for="account" class="th1">用户名</label>
                <input class="th2" type="text" id="account" placeholder="请填写用户名" name="account"
                       value="<%out.print(account);%>"/>
            </div>
            <div>
                <label for="password" class="th1">密　码</label>
                <input class="th2" type="password" id="password" placeholder="请输入登录密码" name="password"
                       value="<%out.print(password);%>"/>
            </div>
            <div>
                <label for="password" class="th1">记住密码:</label>
                <input type="checkbox" class="ck" name="ck" value="ck">
            </div>
            <div>
                <input type="submit" value="登录" class="th3"/>
            </div>

        </form>
    </div>
</div>
</body>
</html>
