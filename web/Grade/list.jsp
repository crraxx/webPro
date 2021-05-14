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
    <style type="text/css">
        body{
            color: #F0F8FF;
        }
        a{
            color: white;
        }
    </style>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <script src="${path}/js/jquery.1.8.3.min.js"></script>
    <script type="text/javascript">
        function add(){
            location.assign("grade?mark=toadd");
        }

        function delStudent(id){
            if (confirm("您确定要删除吗")){
                location.assign("grade?mark=del&id="+id);
            }
        }

        function delAllStu(){
            var ck = $("input[name='gradeId']:checked");
            if (ck.length == 0){
                alert("最少选中一个选项");
                return ;
            }
            if (confirm("您确定要删除吗")){
                var ids="";
                $("input[name='gradeId']:checked").each(function (i,e) {
                    ids+=e.value+",";
                });
                location.assign("grade?mark=del&id="+ids);
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
<body>
<%--学生信息获取--%>
<input type="button" value="新增" onclick="add()">
<input type="button" value="批量删除" onclick="delAllStu()">
<table border="1" width="100%。">
    <tr>
        <td>序号</td>
        <td>
            <input type="checkbox" id="allselect" onclick="selectAll()" >
        </td>
        <td>课程id</td>
        <td>课程名</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${gradeList}" var="grade" varStatus="c">
        <tr>
            <td>${c.count}</td>
            <td><input type="checkbox" name="gradeId" value="${grade.id}"></td>
            <td>${grade.id}</td>
            <td>${grade.name}</td>
            <td>
                <a href="">详细信息</a>
                <a href="javaScript:void(0)" onclick="delStudent(${grade.id})">删除</a>
                <a href="">更新</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
