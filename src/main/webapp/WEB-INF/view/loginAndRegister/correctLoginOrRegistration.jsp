<%--
  Created by IntelliJ IDEA.
  User: Irakli
  Date: 7/22/2020
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Welcome <%= request.getParameter("username")%>
    </title>
</head>
<body>
<h1>Welcome <%= request.getParameter("username")%></h1>
</body>
</html>
