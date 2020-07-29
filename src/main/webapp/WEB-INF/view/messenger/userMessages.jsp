<%@ page import="com.quiz.model.user.User" %><%--
  Created by IntelliJ IDEA.
  User: Quantori
  Date: 7/29/2020
  Time: 12:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <%
        User fromUser = (User)request.getAttribute("fromuser");
        String userName = request.getParameter("username");
    %>
<head>
    <title>Messenger | <%=fromUser.getFirstName()%> <%=fromUser.getLastName()%></title>
</head>
<body>
    <form action="homepage" method="post">
        <input type="hidden" name="username" value=<%=userName%>>
        <input type="submit" value="Homepage">
    </form>

    <form action="messenger" method="post">
        <input type="hidden" name="username" value=<%=userName%>>
        <input type="submit" value="Messenger Main Page">
    </form>

</body>
</html>
