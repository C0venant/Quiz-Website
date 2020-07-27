<%@ page import="com.quiz.model.quiz.Quiz" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/27/2020
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>take Quiz</title>
</head>
<body>

    <form action="startQuiz" method="post">
        <%
            Quiz quiz = (Quiz) request.getAttribute("quiz");
            out.println("<h4> Quiz by: " + quiz.getQuizAuthor() + "</h4>");
            out.print("<h3>" + quiz.getQuizName() + "</h3>");
            out.print("<h4> overallGrade: " + quiz.getOverallGrade() + "</h4>");
        %>
        <%
            Quiz quiz1 = (Quiz)request.getAttribute("quiz");
            out.print("<input type=\"hidden\" id=\"quiz2\" name=\"quizName\" value=\""+quiz1.getQuizName()+"\">");
        %>
        <input type="hidden" id="user" name="username" value=<%= request.getParameter("username")%>>
        <input type="submit" value="start">
    </form>


</body>
</html>
