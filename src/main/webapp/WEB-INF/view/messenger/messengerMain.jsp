<%--suppress ALL --%>
<%--suppress unchecked --%>
<%@ page import="com.quiz.database.RequestDaoImplementation" %>
<%@ page import="com.quiz.model.user.User" %>
<%@ page import="java.util.List" %><%--
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

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .button {
            border: none;
            color: white;
            padding: 12px 16px;
            font-size: 16px;
            cursor: pointer;
        }

        .button1 {
            background-color: DodgerBlue;
        }

        .button2 {
            background-color: green;
        }

        /* Darker background on mouse-over */
        .button1:hover {
            background-color: RoyalBlue;
        }

        .button2:hover {
            background-color: darkgreen;
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
</head>
<body>

<%
    List<User> friendsLst = (List<User>) request.getAttribute("friends");
    RequestDaoImplementation requestDao = (RequestDaoImplementation) request.getAttribute("requestDao");
    String userName = request.getParameter("username");
%>

<form name="homepageForm" action="homepage" method="post">
    <button class="button button1" onclick="homepageForm.submit()"><i class="fa fa-home"></i> Homepage</button>
    <input type="hidden" name="username" value=<%=userName%>>
</form>

<form name="readForm" action="markAllAsRead" method="post">
    <button class="button button2" onclick="readForm.submit()"><i class="fa fa-check"></i> Mark all as read</button>
    <input type="hidden" name="username" value=<%=userName%>>
</form>

<h4>Your friends:</h4><br>
<%
    int id = 0;
    for (User usr : friendsLst) {
        int n = requestDao.getUnreadMessagesFromConcreteUser(userName, usr.getLoginName()).size();
        String val = usr.getLoginName() + "(" + usr.getFirstName() + " " + usr.getLastName() + ")";
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

