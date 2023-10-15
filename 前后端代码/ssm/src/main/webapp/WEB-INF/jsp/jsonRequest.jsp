<%--
  Created by IntelliJ IDEA.
  User: 35519
  Date: 2021/3/21
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>niaho</title>
    <script src="https://cdn.staticfile.org/jquery/1.10.0/jquery.min.js"/>
    <script  type="text/javascript">
        $(function(){
            $("#nihao").click(function (){
                alert("niaho");
                $.ajax({
                    url:"${pageContext.request.contextPath}/supplierLogin",
                    method:"post",
                    data:'{"name":"nihao","pwd":"123456"}',
                    contentType:"application/json",
                    dataType:"json",
                    success:function (data) {
                        alert("1");
                        alert(data);
                    }
                });
            })

        })
    </script>
</head>
<body>
<form action="supplierLogin" method="post">
    username: <input name="name" type="text" >
    password: <input type="text" name="pwd"><br>
    <input type="submit" value="提交">
    <%--    supLogin?name=niaho&pwd=1--%>
</form>
</body>
</html>
