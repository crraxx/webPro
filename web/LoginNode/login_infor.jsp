<%--
  Created by IntelliJ IDEA.
  User: 17509
  Date: 2021/4/9
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <script src="js/jquery.1.8.3.min.js" type="text/css"></script>
    <script type="text/javascript">
       /* function delInfor(id){
            if (confirm("您确定要删除吗")){
                location.assign("student?mark=node&id="+id);
            }
        }*/
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
<%--学生信息获取--%>
<%--<input type="button" value="新增" onclick="add()">--%>
<table border="1" width="100%。">
    <tr>
        <td>序号</td>
        <td>账号</td>
        <td>IP地址</td>
        <td>操作事件</td>
    </tr>
    <c:forEach items="${logininfors}" var="lof" >
        <tr>
            <td>${lof.id}</td>
            <td>${lof.account}</td>
            <td>${lof.ip}</td>
            <td>
                <fmt:formatDate value="${lof.login_time}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
