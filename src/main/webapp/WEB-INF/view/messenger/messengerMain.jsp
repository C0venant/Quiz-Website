<%@ page import="com.quiz.model.user.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.quiz.database.RequestDaoImplementation" %><%--
  Created by IntelliJ IDEA.
  User: Quantori
  Date: 7/28/2020
  Time: 10:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Messenger</title>
</head>
<body>
    <%
        List<User> friendsLst = (List<User>)request.getAttribute("friends");
        RequestDaoImplementation requestDao = (RequestDaoImplementation)request.getAttribute("requestDao");
        String userName = request.getParameter("username");
    %>
    <form action="homepage" method="post">
        <input type="hidden" name="username" value=<%=userName%>>
        <input type="submit" value="Homepage">
    </form>

    <form action="markAllAsRead" method="post">
        <input type="hidden" name="username" value=<%=userName%>>
        <input type="submit" value="Mark all as read">
    </form>

    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        .notification {
            background-color: #555;
            color: white;
            text-decoration: none;
            padding: 13px 23px;
            position: relative;
            display: inline-block;
            border-radius: 5px;
        }

        .notification:hover {
            background: red;
        }

        .notification .badge {
            position: absolute;
            top: -10px;
            right: -10px;
            padding: 5px 10px;
            border-radius: 50%;
            background-color: red;
            color: white;
        }
    </style>

    <h4>Your friends:</h4><br>
    <%
        int id = 0;
        for(User usr : friendsLst){
            int n = requestDao.getUnreadMessagesFromConcreteUser(userName, usr.getLoginName()).size();
            String val =  usr.getLoginName() + "(" + usr.getFirstName() + " " + usr.getLastName() + ")";
            out.print("<form name=\"requestForm" + id + "\"action=\"messageToUser\" class=\"notification\" method=\"post\">");
            out.print("<input type=\"hidden\" name=\"username\" value=" + userName + ">");
            out.print("<input type=\"hidden\" name=\"fromuser\" value=" + usr.getLoginName() + ">");
            out.print("<span onclick=\"requestForm" + id + ".submit()\">" + val + "</span>");
            out.print("<span class=\"badge\">" + n + "</span></form><br>");
            id++;
        }
    %>
</body>
</html>

