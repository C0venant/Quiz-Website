<%@ page import="com.quiz.model.quiz.Quiz" %>
<%@ page import="com.quiz.model.quiz.question.QuestionBasic" %>
<%@ page import="com.quiz.model.quiz.question.utils.QuestionType" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/28/2020
  Time: 00:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>question</title>
</head>
<body>
<%
    Quiz quiz = (Quiz) request.getAttribute("quiz");
    int index = (Integer) request.getAttribute("questionIndex");
    QuestionBasic question = quiz.getQuestions().get(index);
    int questionNum = index + 1;
    out.println("<h4> question " + questionNum + "</h4>");
    out.println("<h4> " + question.getBody() + "</h4>");
    if(question.getType().equals(QuestionType.BLANK)){

    }else if(question.getType().equals(QuestionType.TEST)){

    }else{
        out.print("<textarea id=\"answerBody\" name=\"userAnswer\" rows=\"5\" " +
                "cols=\"50\" placeholder=\"Fill in with your answer\"></textarea><br><br>");
    }
%>

</body>
</html>
