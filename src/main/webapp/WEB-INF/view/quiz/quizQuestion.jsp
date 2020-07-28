<%@ page import="com.quiz.model.quiz.Quiz" %>
<%@ page import="com.quiz.model.quiz.question.QuestionBasic" %>
<%@ page import="com.quiz.model.quiz.question.utils.QuestionType" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/28/2020
  Time: 00:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>question</title>
</head>
<body>
<form action="fetchNextQuestion" method="post">
    <%
        Quiz quiz = (Quiz) request.getAttribute("quiz");
        int index = (Integer) request.getAttribute("questionIndex");
        QuestionBasic question = quiz.getQuestions().get(index);
        int nextNum = index + 1;
        if(question.getType().equals(QuestionType.BLANK)){

        }else if(question.getType().equals(QuestionType.TEST)){
            out.println("<h4> question " + nextNum + "</h4>");
            out.println("<h4> " + question.getBody() + "</h4>");

        }else{
            out.println("<h4> question " + nextNum + "</h4>");
            out.println("<h4> " + question.getBody() + "</h4>");
            out.print("<textarea id=\"answerBody\" name=\"userAnswer\" rows=\"5\" " +
                    "cols=\"50\" placeholder=\"Fill in with your answer\"></textarea><br><br>");
        }

        if(index > 0){
            out.print("<input type=\"submit\" name=\"nextQuestion\" value=\""+"previous"+"\">");
        }
        if(index < quiz.getQuestions().size()-1){
            out.print("<input type=\"submit\" name=\"nextQuestion\" value=\""+"next"+"\">");
        }else{
            out.print("<input type=\"submit\" name=\"nextQuestion\" value=\""+"finish"+"\">");
        }
        out.print("<input type=\"hidden\" id=\"quiz2\" name=\"quizName\" value=\""+quiz.getQuizName()+"\">");
        out.print("<input type=\"hidden\" id=\"index\" name=\"index\" value=\""+index+"\">");
    %>
    <input type="hidden" id="user" name="username" value=<%= request.getParameter("username")%>>
</form>
</body>
</html>
