<%@ page import="com.quiz.database.UserDaoImplementation" %>
<%@ page import="com.quiz.model.request.Request" %>
<%@ page import="com.quiz.model.user.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Quantori
  Date: 7/28/2020
  Time: 3:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Friend requests</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .btn {
            background-color: DodgerBlue;
            border: none;
            color: white;
            padding: 12px 16px;
            font-size: 16px;
            cursor: pointer;
        }

        /* Darker background on mouse-over */
        .btn:hover {
            background-color: RoyalBlue;
        }

        textarea {
            width: 40%;
            height: 50px;
            padding: 12px 20px;
            box-sizing: border-box;
            border: 2px solid #ccc;
            border-radius: 4px;
            background-color: #f8f8f8;
            font-size: 16px;
            resize: none;
        }

        input[type=button], input[type=submit], input[type=reset] {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 16px 32px;
            text-decoration: none;
            margin: 4px 2px;
            cursor: pointer;
        }

        .content-table {
            table-layout: auto;
            width: 100%;
            border-collapse: collapse;
            margin: 25px 0;
            font-size: 0.9em;
            min-width: 400px;
            border-radius: 5px 5px 0 0;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
        }

        .content-table thead tr {
            background-color: #009879;
            color: #ffffff;
            text-align: center;
            font-weight: bold;
        }

        .content-table th,
        .content-table td {
            padding: 12px 15px;
        }

        .content-table tbody tr {
            border-bottom: 1px solid #dddddd;
        }

        .content-table tbody tr:nth-of-type(even) {
            background-color: #f3f3f3;
        }

        .content-table tbody tr:last-of-type {
            border-bottom: 2px solid #009879;
        }
    </style>
</head>
<body>
<form name="homepageForm" action="homepage" method="post">
    <button class="btn" onclick="homepageForm.submit()"><i class="fa fa-home"></i> Homepage</button>
    <input type="hidden" name="username" value=<%=request.getParameter("username")%>>
</form>

<table class="content-table">
    <thead>
    <tr>
        <th>Enter friend's user name</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th>
            <form name="proceedForm" action="proceedSendFriendRequest" method="post">
                <h1>Enter friend's user name</h1>
                <input type="hidden" id="user" name="username" value=<%= request.getParameter("username")%>>
                <label for="touser"></label><textarea rows="5" cols="100" name="touser" id="touser" required></textarea><br><br>
                <input type="button" onclick="proceedForm.submit()" value="Add friend">
            </form>
        </th>
    </tr>
    </tbody>
</table>

<h4>Your friend requests:</h4>

<table class="content-table">
    <thead>
    <tr>
        <th>Username</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Accept/Reject</th>
    </tr>
    </thead>
    <tbody>
    <%
        @SuppressWarnings("unchecked") List<Request> friendReqs = (List<Request>) request.getAttribute("friendRequests");
        UserDaoImplementation userDao = (UserDaoImplementation) request.getAttribute("userDao");
        if (friendReqs != null) {
            for (Request req : friendReqs) {
                User getUser = userDao.getUser(req.getFromUser());
                out.print("<tr><th>" + req.getFromUser() + "</th>");
                out.print("<th>" + getUser.getFirstName() + "</th>");
                out.print("<th>" + getUser.getLastName() + "</th>");
                out.print("<th><span>&#9679;</span>" + "<a href=\"/quiz-trial/acceptOrReject?fromUser=" + req.getFromUser() +
                        "&toUser=" + req.getToUser() + "&id=" + req.getId() + "\">" + "Yes/No" + "</a></th><tr>");
            }
        }
    %>
    </tbody>
</table>

</body>
</html>
