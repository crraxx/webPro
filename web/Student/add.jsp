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
    <style type="text/css">
        body {
            color: #F0F8FF;
        }

        a {
            color: white;
        }
    </style>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript" src="${path}/js/jquery.1.8.3.min.js"></script>
    <script type="text/javascript">
        function checkNum(num) {
            var num = $("#num").val();
            /* $.post("
            ${path}/student?mark=check",{num:num},function (res) {
                $("#span1").html(res);
            });*/
            var result = false;
            $.ajax({
                url: "${path}/student",
                type: "post",
                data: {num: num, mark: "check"},
                async: false,//锁定页面,将异步转化为同步
                success: function (res) {
                    if (res == 0) {
                        $("#span1").html("√");
                        result =  true;
                    } else if (res > 0) {
                        $("#span1").html("账户已存在")
                    } else {
                        $("#span1").html("服务器异常")
                    }
                }
            });
            return result;
        }
    </script>
    <script type="text/javascript">
        function checkForm() {

            var ckres = checkNum();
            return ckres;
            //学号为空验证
            if ($("#num").value.length == 0) {
                $("#span1").text("学号不能为空");
                return false;
            } else if (ckres) {
                $("#span1").text("");
            }
            //姓名为空验证
            if ($("#name").value.length == 0) {
                $("#span2").text("姓名不能为空");
                return false;
            } else {
                $("#span2").text("");
            }

            //手机号为空验证
            var reg = /(13|15|18)\d{9}$/
            if ($("#mobile").value.length == 0) {
                $("#span3").text("电话不能为空");
                return false;
            } else if (!reg.test($("#mobile").value)) {
                $("#span3").text("电话格式错误");
                return false;
            } else {
                $("#span3").text("");
            }

            //地址验证
            if ($("#address").value.length == 0) {
                $("#span4").text("地址不能为空");
                return false;
            } else {
                $("#span4").text("");
            }

            //课程验证
            var flag = 0;
            var courseObj = $("#course");
            for (var i = 0; i < courseObj.length; i++) {
                if (courseObj[i].checked == false) {
                    flag++;
                }
            }
            if (flag == courseObj.length) {
                $("#span5").text("必须有一个爱好");
                return false;
            } else {
                $("#span5").text("");
            }
        }
    </script>
</head>
<body>
<form action="${path}/student" method="post" onsubmit="return checkForm()">
    <%--  type="hidden" 会提交 但不显示   --%>
    <input type="hidden" name="mark" value="save">
    <table border="1" width="30%">
        <tr>
            <td>学号</td>
            <td><input type="text" id="num" name="num" onkeyup="checkNum(this.value)"><span id="span1"></span></td>
        </tr>
        <tr>
            <td>姓名</td>
            <td><input type="text" id="name" name="name" value=""><span id="span2"></span></td>
        </tr>
        <tr>
            <td>性别</td>
            <td>
                <input type="radio" name="sex" value="男" checked="checked">男
                <input type="radio" name="sex" value="女">女
            </td>
        </tr>
        <tr>
            <td>生日</td>
            <td>
                <input type="date" name="birthday" id="birthday" value="">
            </td>
        </tr>
        <tr>
            <td>电话</td>
            <td>
                <input type="text" id="mobile" name="mobile" value="">
                <span id="span3"></span>
            </td>
        </tr>
        <tr>
            <td>地址</td>
            <td>
                <input type="text" name="address" id="address" value="">
                <span id="span6"></span>
            </td>
        </tr>
        <tr>
            <td>年级</td>
            <td>
                <select name="gradeId">
                    <c:forEach items="${gradeList}" var="grade">
                        <option value="${grade.id}">${grade.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>课程</td>
            <td>
                <c:forEach items="${courseList}" var="co">
                    <input type="checkbox" name="course" value="${co.id}">${co.name}
                </c:forEach>
                <span id="span5"></span>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交">
                <a href="${path}/Student/list.jsp"> 返回 </a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
