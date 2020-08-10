<%@ page import="com.quiz.model.quiz.Quiz" %>
<%@ page import="com.quiz.model.quiz.question.QuestionBasic" %>
<%@ page import="com.quiz.model.quiz.question.utils.QuestionType" %>
<%@ page import="com.quiz.model.quiz.question.QuestionTest" %>
<%@ page import="com.quiz.model.quiz.question.QuestionFillBlank" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/27/2020
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false"%>
<html>
<head>
    <title>Quiz view</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        .button {
            background-color: DodgerBlue;
            border: none;
            color: white;
            padding: 12px 16px;
            font-size: 16px;
            cursor: pointer;
        }

        .button1 {
            background-color: green;
        }

        .button2 {
            background-color: red;
        }

        /* Darker background on mouse-over */
        .button:hover {
            background-color: RoyalBlue;
        }

        .button1:hover {
            background-color: darkgreen;
        }

        .button2:hover {
            background-color: darkred;
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
    <%
        Quiz quiz1 = (Quiz)request.getAttribute("quiz");
        out.println("<tr><th><h4>Quiz name : "+quiz1.getQuizName()+"</h4>");
        out.println("<h4>Quiz grade : "+quiz1.getOverallGrade()+"</h4></th></tr>");
    %>
    </tbody>
</table>
    <table class="content-table">
        <thead>
        <tr>
            <th>Quiz Questions</th>
        </tr>
        </thead>
        <tbody>
    <%
        Quiz quiz = (Quiz)request.getAttribute("quiz");
        List<QuestionBasic> list = quiz.getQuestions();
        for (QuestionBasic q : list) {
            out.print("<tr><th>");
            if(q.getImageFile()!=null && !q.getImageFile().equals("")){
                out.print("<img src=\""+q.getImageFile()+"\" alt=\"image\" style=\"width:104px;height:142px;\">");
            }
            if (q.getType().equals(QuestionType.TEST)) {
                QuestionTest qt = (QuestionTest) q;
                out.println("<h4> max grade: " + qt.getMaxGrade() + "</h4>");
                out.println("<h4>" + qt.getBody() + "</h4>");
                for (int j = 0; j < qt.getAnswers().size(); j++) {
                    int index = j +1;
                    out.println("<h4>" + index +") " + qt.getAnswers().get(j) + "</h4>");
                }
            } else if (q.getType().equals(QuestionType.BLANK)) {
                QuestionFillBlank qb = (QuestionFillBlank) q;
                out.println("<h4> max grade: " + qb.getMaxGrade() + "</h4>");
                out.println("<h4>" + qb.toString() + "</h4>");
            } else {
                out.println("<h4> max grade: " + q.getMaxGrade() + "</h4>");
                out.println("<h4>" + q.toString() + "</h4>");
            }
            out.print("</th></tr>");
        }

    %>


    <tr><th>
<form name="delForm" action="deleteQuiz" method="post">
    <%
        Quiz quiz3 = (Quiz)request.getAttribute("quiz");
        out.print("<input type=\"hidden\" id=\"quiz\" name=\"quizName\" value=\""+quiz3.getQuizName()+"\">");
    %>
    <input type="hidden" id="user" name="username" value=<%= request.getParameter("username")%>>
    <button class="button button2" onclick="delForm.submit()"><i class="fa fa-trash"></i> Delete</button>
</form>

<form name="enrollForm" action="enrollQuiz" method="post">
    <%
        Quiz quiz2 = (Quiz)request.getAttribute("quiz");
        out.print("<input type=\"hidden\" id=\"quiz2\" name=\"quizName\" value=\""+quiz2.getQuizName()+"\">");
    %>
    <input type="hidden" id="user2" name="username" value=<%= request.getParameter("username")%>>
    <button class="button button1" onclick="enrollForm.submit()"><i class="fa fa-pencil"></i> Enroll</button>
</form>
    </th></tr>
        </tbody>
    </table>
</body>
</html>
