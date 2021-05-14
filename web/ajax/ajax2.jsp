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
    <script type="text/javascript">
        function checkAccount(account) {
            //1.创建XMLHTTPRequest对象
            var httpObj = new XMLHttpRequest();
            //2.使用XMLHTTPRequest 对象向服务器发送异步请求
            httpObj.open("post","${path}/ajax1?account="+account,true);//封装请求信息
            //设置请求头,内部的提交格式为表单的默认格式
            httpObj.setRequestHeader("content-type","applicationCache/x-www-form-urlencoded")
            httpObj.send("account="+account+"&age=23");//信息放在请求体中
            //3.从XMLHTTPRequest对象取出响应的数据
            //当请求发送之后,会触发onreadystatechange事件
            httpObj.onreadystatechange=function (){
                if (httpObj.readyState==4&&httpObj.status==200){
                    document.getElementById("msgid").innerHTML = httpObj.responseText;
                }
            }
        }
    </script>
</head>
<body>
<%--
enctype="multipart/form-data"提交文件
applicationCache/x-www-form-urlencoded 表单默认的提交方式  键=值 & 键 = 值
--%>
<input type="text" name="account" onblur="checkAccount(this.value)" ><span id="msgid"></span>
<input type="password" name="password">
</body>
</html>
