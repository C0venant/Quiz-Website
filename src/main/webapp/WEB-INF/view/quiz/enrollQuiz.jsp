<%@ page import="com.quiz.model.quiz.Quiz" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/27/2020
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>take Quiz</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }
        .content-table {
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

<table class="content-table">
    <thead>
    <tr>
        <th>Quiz Info</th>
    </tr>
    </thead>
    <tbody>
    <tr><th>

    <form action="startQuiz" method="post">
        <%
            Quiz quiz = (Quiz) request.getAttribute("quiz");
            out.println("<h4> Quiz by : " + quiz.getQuizAuthor() + "</h4>");
            out.print("<h3>" + quiz.getQuizName() + "</h3>");
            out.print("<h4> overallGrade : " + quiz.getOverallGrade() + "</h4>");
        %>
        <%
            Quiz quiz1 = (Quiz)request.getAttribute("quiz");
            out.print("<input type=\"hidden\" id=\"quiz2\" name=\"quizName\" value=\""+quiz1.getQuizName()+"\">");
        %>
        <input type="hidden" id="user" name="username" value=<%= request.getParameter("username")%>>
        <input type="submit" value="start">
    </form>

    </th></tr></tbody></table>
</body>
</html>
