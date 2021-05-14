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
            //1.创建XMLHttprequest对象
            var httpObj = new XMLHttpRequest();
            //2.使用XMLHTTPRequest对象向服务器发送异步请求
            httpObj.open("get","${path}/ajax1",true);//封装请求信息
            httpObj.send("account="+account);
            //3.从XMLHttpRequest对象中取出响应的数据
            //当请求发送之后,会触发onreadystatechange事件
            httpObj.onreadystatechange=function (){
                //status Http的状态码,200表示响应正常
                if (httpObj.readyState==4&&httpObj.status==200){
                    //responseText获取响应文本内容
                    document.getElementById("msgid").innerHTML = httpObj.responseText;
                }
            }
        }
    </script>
</head>
<body>
<input type="text" name="account" onblur="checkAccount(this.value)" ><span id="msgid"></span>
<input type="password" name="password">
</body>
</html>
