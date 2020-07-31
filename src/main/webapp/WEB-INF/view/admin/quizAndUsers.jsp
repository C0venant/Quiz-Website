<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/31/2020
  Time: 00:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>info list</title>
    <style>

        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        .content-table {
            border-collapse: collapse;
            margin: 25px 0;
            font-size: 0.9em;
            min-width: 180px;
            border-radius: 5px 5px 0 0;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
        }

        .content-table thead tr {
            background-color: #009879;
            color: #ffffff;
            text-align: left;
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

<form action="returnHome" method="post">
    <input type=hidden name=username value=<%=request.getParameter("username")%>>
    <input type="submit" value="Home">
</form>

<table class="content-table">
    <thead>
    <tr>
        <th>_____Users_____</th>

    </tr>
    </thead>
    <tbody>
    <%
        List<String> userList = (List<String>)request.getAttribute("userList");
        for(String s : userList){
            out.print("<tr><th><span>&#9679;</span>"+"<a href=\"/quiz-trial/loadUserView?globalUser="+s+
                    "&username="+request.getParameter("username")+"\">"+s+"</a></th></tr>");
        }

    %>
    </tbody>
</table>

<table class="content-table">
    <thead>
    <tr>
        <th>_____Quizzes_____</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<String> quizList = (List<String>)request.getAttribute("quizList");
        for(String s : quizList){
            out.print("<tr><th><span>&#9679;</span>"+"<a href=\"/quiz-trial/loadQuizView?quizName="+s+
                    "&username="+request.getParameter("username")+"\">"+s+"</a></th></tr>");
        }
    %>
    </tbody>
</table>

</body>
</html>
