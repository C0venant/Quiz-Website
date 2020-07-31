<%@ page import="com.quiz.model.quiz.Quiz" %>
<%@ page import="com.quiz.model.quiz.question.QuestionBasic" %>
<%@ page import="com.quiz.model.quiz.question.utils.QuestionType" %>
<%@ page import="com.quiz.model.quiz.question.QuestionTest" %>
<%@ page import="com.quiz.model.quiz.question.QuestionFillBlank" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/30/2020
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Grade Quiz</title>
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
<form action="returnMarked" method="post">

    <table class="content-table">
        <thead>
        <tr>
            <th>Quiz Info</th>
        </tr>
        </thead>
        <tbody>
        <%
            Quiz quiz2 = (Quiz)request.getAttribute("quiz");
            out.println("<tr><th><h4>Quiz name : "+quiz2.getQuizName()+"</h4>");
            out.println("<h4>Quiz grade : "+quiz2.getOverallGrade()+"</h4></th></tr>");
        %>
        </tbody>
    </table>

    <table class="content-table">
        <thead>
        <tr>
            <th>User results</th>
        </tr>
        </thead>
        <tbody>

    <%
        Quiz quiz = (Quiz)request.getAttribute("quiz");
        String checkReq = request.getParameter("checkReq");
        List<String> userAnswers = (List<String>)request.getAttribute("userAnswers");
        List<QuestionBasic> list = quiz.getQuestions();
        int counter = 0;
        for (QuestionBasic q : list) {
            out.print("<tr><th>");
            if(q.getImageFile()!=null && !q.getImageFile().equals("")){
                out.print("<img src=\""+q.getImageFile()+"\" alt=\"image\" style=\"width:104px;height:142px;\">");
            }
            String id = "gradeId"+counter;
            if (q.getType().equals(QuestionType.TEST)) {
                QuestionTest qt = (QuestionTest) q;
                out.println("<h4> max grade: " + qt.getMaxGrade() + "</h4>");
                out.println("<h4>" + qt.getBody() + "</h4>");
                for (int j = 0; j < qt.getAnswers().size(); j++) {
                    int index = j +1;
                    out.println("<h4>" + index +") " + qt.getAnswers().get(j) + "</h4>");
                }
                out.println("<h4>"+checkReq+" answered: </h4>");
                out.println("<h4>"+userAnswers.get(counter)+"</h4>");
                if(qt.checkAnswer(userAnswers.get(counter))){
                    out.print("<input type=\"text\" id=\""+id+"\" name=\""+"grade"+counter+"\" value=\""+qt.getMaxGrade()+"\" required>");
                }else{
                    out.print("<input type=\"text\" id=\""+id+"\" name=\""+"grade"+counter+"\" value=\""+0+"\"  required>");
                }
            } else if (q.getType().equals(QuestionType.BLANK)) {
                QuestionFillBlank qb = (QuestionFillBlank) q;
                out.println("<h4> max grade: " + qb.getMaxGrade() + "</h4>");
                out.println("<h4>" + qb.toString() + "</h4>");
                out.println("<h4>"+checkReq+" answered: </h4>");
                out.println("<h4>"+userAnswers.get(counter)+"</h4>");
                if(qb.checkAnswer(userAnswers.get(counter))){
                    out.print("<input type=\"text\" id=\""+id+"\" name=\""+"grade"+counter+"\" value=\""+qb.getMaxGrade()+"\" required>");
                }else{
                    out.print("<input type=\"text\" id=\""+id+"\" name=\""+"grade"+counter+"\" value=\""+0+"\" required>");
                }
            } else {
                out.println("<h4> max grade: " + q.getMaxGrade() + "</h4>");
                out.println("<h4>" + q.toString() + "</h4>");
                out.println("<h4>"+checkReq+" answered: </h4>");
                out.println("<h4>"+userAnswers.get(counter)+"</h4>");
                out.print("<input type=\"text\" id=\""+id+"\" name=\""+"grade"+counter+"\" value=\"\" required>");
            }
            counter++;
            out.print("</th></tr>");
        }
        out.print("<input type=\"hidden\" id=\"quiz\" name=\"quizName\" value=\""+quiz.getQuizName()+"\">");

    %>
        </tbody>
    </table>

    <input type="hidden" id="user1" name="checkReq" value=<%= request.getParameter("checkReq")%>>
    <input type="hidden" id="user2" name="username" value=<%= request.getParameter("username")%>>
    <input type="submit" value="return marked">

</form>

</body>
</html>
