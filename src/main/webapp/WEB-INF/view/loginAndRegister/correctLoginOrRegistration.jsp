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
    <form action="addFriend" method="post">
        <input type="hidden" name="fromuser" value= <%= request.getParameter("username")%>>
        <label for="touser"> Enter friend's user name: </label>
        <input type="search" name="touser" id="touser">
        <input type="submit" value="Add friend">
    </form>
    <form action="logout" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
