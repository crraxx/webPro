<%--
  Created by IntelliJ IDEA.
  User: 17509
  Date: 2021/4/10
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript">
        function checkForm() {
            //课程id为空验证
            if ($("#id").value.length == 0) {
                $("#span1").text("年级id不能为空");
                return false;
            }else {
                $("#span1").text("");
            }
            //课程名为空验证
            if ($("#name").value.length == 0) {
                $("#span2").text("年级名不能为空");
                return false;
            }else {
                $("#span2").text("");
            }
        }
    </script>
    <style type="text/css">
        body{
            color: #F0F8FF;
        }
        a{
            color: white;
        }
    </style>
</head>
<body>
<form action="${path}/grade" method="post" onsubmit="return checkForm()">
    <%--  type="hidden" 会提交 但不显示   --%>
    <input type="hidden" name="mark" value="save">
    <table border="1" width="30%">
        <tr>
            <td>年级id</td>
            <td><input type="text" id="id" name="id" value=""><span id="span1"></span></td>
        </tr>
        <tr>
            <td>年级名</td>
            <td><input type="text" id="name" name="name" value=""><span id="span2"></span></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交">
                <a href="list.jsp"> 返回 </a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
