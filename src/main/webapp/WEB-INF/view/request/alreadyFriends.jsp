<%--
  Created by IntelliJ IDEA.
  User: Irakli
  Date: 7/27/2020
  Time: 1:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Send friend request</title>
</head>
<body>
    <form action="homepage" method="post">
        <input type="hidden" name="username" value=<%=request.getParameter("username")%>>
        <input type="submit" value="Homepage">
    </form>

    <form action="friendRequests" method="post">
        <input type="hidden" name="username" value=<%=request.getParameter("username")%>>
        <input type="submit" value="Friend Requests Main Page">
    </form>

    <form action="proceedSendFriendRequest" method="post">
        <h1>User with user name <%=request.getParameter("touser")%> is already your friend. Enter Another: </h1>
        <input type="hidden" id="user" name="username" value=<%= request.getParameter("username")%>>
        <label for="touser"> Enter name here: </label>
        <input type="text" name="touser" id="touser">
        <input type="submit" value="Add friend">
    </form>
</body>
</html>