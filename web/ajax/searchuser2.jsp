<%--
  Created by IntelliJ IDEA.
  User: 17509
  Date: 2021/4/16

  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${path}/js/jquery.1.8.3.min.js"></script>
    <script type="text/javascript">
        function searchUser() {
            var user = document.getElementById("userId").value;
            var httpObj = new XMLHttpRequest();
            httpObj.open("get", "${path}/ajax2?user=" + user, true);
            httpObj.send();
            httpObj.onreadystatechange = function () {
                if (httpObj.readyState == 4 && httpObj.status == 200) {
                    //json格式的字符串解析为javaScript对象
                    var jsobjs = $.parseJSON(httpObj.responseText);
                    var str = "";
                    for (i = 0; i < jsobjs.length; i++) {
                        str += jsobjs[i].name + ":" + jsobjs[i].age + "<br/>";
                    }
                    document.getElementById("shouid").innerHTML = str;
                }
            }
        }
    </script>
</head>
<body>
<input type="text" id="userId">
<input type="button" value="查询" onclick="searchUser()">
<div id="shouid">

</div>
</body>
</html>
