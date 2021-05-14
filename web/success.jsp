<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        a{
            color: white;
        }
        h3{
            margin: 30px 40px;
        }
    </style>
    <script>
        function test() {
            var c = confirm("您确定要退出吗");
            if (c) {
                window.location.replace("login");
            }
        }
    </script>
</head>
<body>
<%
    System.out.println("laile");
    %>
<table border="1" width="100%" >
    <tr height="100px"  style="background-image: url(img/导航.jpg); background-size: 100% 140% ">
        <td colspan="2">
            <h1 style="color:black;">欢迎${admin.account}登录</h1><br/>
            <input type="submit" value="安全退出" onclick=" test()"/>
        </td>
    </tr>
    <tr >
        <td style="width: 200px;background-image: url(img/菜单.jpg); background-size: 200px 600px" valign="top" height="600px" >
            <h3 >
                <a href="${path}/student?mark=list" target="workspace"> 学生信息管理</a><br>
                <a href="${path}/course?mark=list" target="workspace"> 课程信息管理</a><br>
                <a href="${path}/grade?mark=list" target="workspace"> 年级信息管理</a><br>
                <a href="${path}/checkCountry.jsp" target="workspace"> 地区信息管理</a><br>
                <a href="${path}/loginNode?mark=list" target="workspace"> 查看日志 </a>
            </h3>
        </td>
        <td style="background-image: url(img/内容区.jpg);background-size: 100% 100%">
            <iframe src="" name="workspace" width="100%" height="600px" frameborder="0"></iframe>
        </td>
    </tr>
</table>
</body>
</html>
