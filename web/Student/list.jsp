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
    <script src="${path}/js/jquery.1.8.3.min.js"></script>
    <style type="text/css">
        body{
            color: #F0F8FF;
        }
        a{
            color: white;
        }
    </style>
    <script type="text/javascript">
        function updateStudent(id){
            alert(id);
            location.assign("student?mark=updateStu&id="+id);
        }

        function add(){
            location.assign("student?mark=toadd");
        }

        function delStudent(id){
            if (confirm("您确定要删除吗")){
                location.assign("student?mark=del&id="+id);
            }
        }

        function delAllStu(){
            var ck = $("input[name='studenId']:checked");
            if (ck.length == 0){
                alert("最少选中一个选项");
                return ;
            }
            if (confirm("您确定要删除吗")){
                var ids="";
                $("input[name='studenId']:checked").each(function (i,e) {
                    ids+=e.value+",";
                });
                location.assign("student?mark=del&id="+ids);
            }
        }

        function selectAll() {

            if($(":Checkbox").attr("checked") != "checked"){
                $(":Checkbox").attr("checked",false);
            }else{
                $(":Checkbox").attr("checked",true);
            }
        }
    </script>
</head>
<body >
<%--学生信息获取--%>
<input type="button" value="新增" onclick="add()">
<input type="button" value="批量删除" onclick="delAllStu()">
<table border="1" width="100%。">
    <tr>
        <td>序号</td>
        <td>
            <input type="checkbox" id="allselect" onclick="selectAll()">
        </td>
        <td>学生id</td>
        <td>学号</td>
        <td>姓名</td>
        <td>性别</td>
        <td>生日</td>
        <td>电话</td>
        <td>地址</td>
        <td>班级</td>
        <td>课程</td>
        <td>操作人</td>
        <td>操作时间</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${studentList}" var="student" varStatus="s">
        <tr>
            <td>${s.count}</td>
            <td><input type="checkbox" name="studenId" value="${student.id}"></td>
            <td>${student.id}</td>
            <td>${student.num}</td>
            <td>${student.name}</td>
            <td>${student.sex}</td>
            <td>${student.birthday}</td>
            <td>${student.mobile}</td>
            <td>${student.address}</td>
            <td>${student.gname}</td>
            <td>${student.cname}</td>
            <td>${student.account}</td>
            <td>
                <fmt:formatDate value="${student.oper_time}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
            </td>
            <td>
                <a href="">详细信息</a>
                <a href="javaScript:void(0)" onclick="delStudent(${student.id})">删除</a>
                <a href="javaScript:void(0)" onclick="updateStudent(${student.id})">更新</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
