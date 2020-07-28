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
</head>
<body>
<form action="deleteQuiz" method="post">
    <%
        Quiz quiz = (Quiz)request.getAttribute("quiz");
        out.println("<h4>Quiz name:"+quiz.getQuizName()+"</h4>");
        out.println("<h4>Quiz grade:"+quiz.getOverallGrade()+"</h4>");
        out.print("<h4>" + "-----------------------------------------------------------------" + "</h4>");
        List<QuestionBasic> list = quiz.getQuestions();
        for (QuestionBasic q : list) {
            if (q.getType().equals(QuestionType.TEST)) {
                QuestionTest qt = (QuestionTest) q;
                out.println("<h4> max grade: " + qt.getMaxGrade() + "</h4>");
                out.println("<h4>" + qt.getBody() + "</h4>");
                for (int j = 0; j < qt.getAnswers().size(); j++) {
                    out.println("<h4>" + j + ") " + qt.getBody() + "</h4>");
                }
            } else if (q.getType().equals(QuestionType.BLANK)) {
                QuestionFillBlank qb = (QuestionFillBlank) q;
                out.println("<h4> max grade: " + qb.getMaxGrade() + "</h4>");
                out.println("<h4>" + qb.toString() + "</h4>");
            } else {
                out.println("<h4> max grade: " + q.getMaxGrade() + "</h4>");
                out.println("<h4>" + q.toString() + "</h4>");
            }

            out.print("<h4>" + "-----------------------------------------------------------------" + "</h4>");
        }
        out.print("<input type=\"hidden\" id=\"quiz\" name=\"quizName\" value=\""+quiz.getQuizName()+"\">");

    %>
    <input type="hidden" id="user" name="username" value=<%= request.getParameter("username")%>>
    <input type="submit" value="delete">
</form>

<form action="enrollQuiz" method="post">
    <%
        Quiz quiz1 = (Quiz)request.getAttribute("quiz");
        out.print("<input type=\"hidden\" id=\"quiz2\" name=\"quizName\" value=\""+quiz1.getQuizName()+"\">");
    %>
    <input type="hidden" id="user2" name="username" value=<%= request.getParameter("username")%>>
    <input type="submit" value="enroll">
</form>

</body>
</html>
