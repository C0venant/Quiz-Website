<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/16/2020
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>testResult</title>
</head>
<body>
<h1>this is test</h1>>
username and password you entered is :<%
  out.print(request.getAttribute("user")+" ");
  out.println(request.getAttribute("password"));
%>

<h4>this is another way of showing stuff: ${user} ${password}</h4>
</body>
</html>
