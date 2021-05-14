<%--
  Created by IntelliJ IDEA.
  User: 17509
  Date: 2021/4/16
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${path}/js/jquery.1.8.3.min.js"></script>
    <script type="text/javascript">
        function checkAccount(account) {
           /* $.get("${path}/ajax1",{account:account},function (res) {
                $("#msgid").html(res);
            }) */
            $.post("${path}/ajax1",{account:account},function (res) {
                $("#msgid").html(res);
            })
        }
    </script>
</head>
<body>
<input type="text" name="account" onblur="checkAccount(this.value)" ><span id="msgid"></span>
<input type="password" name="password">
</body>
</html>
