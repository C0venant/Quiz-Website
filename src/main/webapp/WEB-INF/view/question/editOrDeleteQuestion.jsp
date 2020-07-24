<%@ page import="com.quiz.model.quiz.question.QuestionBasic" %>
<%@ page import="com.quiz.model.quiz.question.utils.QuestionType" %>
<%@ page import="com.quiz.model.quiz.question.QuestionTest" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/24/2020
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit question</title>
</head>
<body>
    <form action="editQuestion" method="post">
        <input type="hidden" id="user" name="username" value=<%= request.getParameter("username")%>>
        <input type="hidden" id="id" name="id" value=<%= request.getParameter("id")%>>
        <%
            QuestionBasic q = (QuestionBasic) request.getAttribute("question");
            out.print("<h4>Question Type: "+q.getType()+"</h4>");
            out.print("<input type=\"hidden\" id=\"t\" name=\"type\" value="+q.getType()+">");
            out.print("<textarea id=\"questionBody\" name=\"body\" rows=\"5\" cols=\"50\" placeholder=\"Fill in\" required>");
            out.print(q.getBody()+"</textarea><br><br>");
            out.print("<label for=\"grade\">Max Grade:</label>");
            out.print("<input type=\"text\" id=\"grade\" name=\"maxGrade\" value=\""+q.getMaxGrade()+"\"><br><br>");
            out.print("<label for=\"image\">Image File:</label>");
            out.print("<input type=\"text\" id=\"image\" name=\"imageFile\" value=\""+q.getImageFile()+"\"><br><br>");
            if(q.getType().equals(QuestionType.BLANK)){
                out.print("<label for=\"answer\">Correct Answer:</label>");
                out.print("<input type=\"text\" id=\"answer\" name=\"correctAnswer\" value=\""+q.getCorrectAnswer()+"\"><br><br>");
            }else if(q.getType().equals(QuestionType.TEST)){
                q = (QuestionTest)q;
                out.print("<label for=\"answer\">Correct Answer:</label>");
                out.print("<input type=\"text\" id=\"answer\" name=\"correctAnswer\" value=\""+q.getCorrectAnswer()+"\"><br><br>");
                out.print("<label for=\"answers\">Probable Answers:</label><br>"+"<h4>please use ',' between answers</h4>");
                out.print("<textarea id=\"answers\" name=\"answers\" rows=\"1\" cols=\"50\" required>");
                for(String answer: ((QuestionTest) q).getAnswers()){out.print(answer+",");}
                out.print("</textarea><br><br>");
            }
        %>

        <input type="submit" value="edit">
    </form>

    <form action="deleteQuestion" method="post">
        <input type="hidden" id="user2" name="username" value=<%= request.getParameter("username")%>>
        <input type="hidden" id="id1" name="id" value=<%= request.getParameter("id")%>>
        <input type="submit" value="delete">
    </form>
</body>
</html>
