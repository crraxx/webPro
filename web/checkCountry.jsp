<%--
  Created by IntelliJ IDEA.
  User: 17509
  Date: 2021/4/17
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${path}/js/jquery.1.8.3.min.js"></script>
    <script type="text/javascript">
        $(function () {
           getArea(0,"provinceId");
        });
        function getCity(pid) {
            getArea(pid,"cityId");
            $("#countyId").html("<option>请选择</option>");
        };
        function getCounty(pid) {
            getArea(pid,"countyId");
        };
        function getArea(pid,selectId){
            $.post("${path}/area",{pid:pid},function (res) {
                var optinStr = "";
                for (var i = 0; i < res.length; i++) {
                    optinStr+="<option value='"+res[i].id+"'>"+res[i].name+"</option>"
                }
                $("#"+selectId).html(optinStr);
            },"json");//返回值的类型为json,自动的将json字符串转为js对象
        }
    </script>
</head>
<body>
    省<select id="provinceId" onchange="getCity(this.value)">
        <option >请选择</option>
    </select>
    市<select id="cityId"  onchange="getCounty(this.value)">
        <option >请选择</option>
    </select>
    县<select id="countyId" >
        <option >请选择</option>
    </select>
</body>
</html>
