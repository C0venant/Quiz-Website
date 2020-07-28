<%@ page import="com.quiz.model.quiz.Quiz" %>
<%@ page import="com.quiz.model.quiz.question.QuestionBasic" %>
<%@ page import="com.quiz.model.quiz.question.utils.QuestionType" %>
<%@ page import="com.quiz.model.quiz.question.QuestionTest" %>
<%@ page import="java.util.List" %><%--
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
        String previousAnswer = (String) request.getAttribute("previousAnswer");
        if(question.getType().equals(QuestionType.BLANK)){
            out.println("<h4> question " + nextNum + "/" + quiz.getQuestions().size() + "</h4>");
            out.println("<h4> " + question.toString() + "</h4>");
            if(previousAnswer == null){
                out.print("<input type =\"text\" name=\"userAnswer\" id=\"ua\" placeholder=\"fill in\"><br><br>");
            }else{
                out.print("<input type =\"text\" name=\"userAnswer\" id=\"ua\" value=\""+previousAnswer+"\"><br><br>");
            }
        }else if(question.getType().equals(QuestionType.TEST)){
            out.println("<h4> question " + nextNum + "/" + quiz.getQuestions().size() + "</h4>");
            out.println("<h4> " + question.getBody() + "</h4>");
            List<String> answers = ((QuestionTest) question).getAnswers();
            if(previousAnswer == null){
                for(int i = 0; i < answers.size(); i++){
                    out.println("<input type=\"radio\" id=\"uar"+i+"\" name=\"userAnswer\" value=\""+answers.get(i)+"\">");
                    out.print("<label for=\"uar"+i+"\">"+answers.get(i)+"</label><br>");
                }
            }else{
                for(int i = 0; i < answers.size(); i++){
                    if(answers.get(i).equals(previousAnswer)){
                        out.println("<input type=\"radio\" id=\"uar"+i+"\" name=\"userAnswer\" value=\""+answers.get(i)+"\"checked>");

                    }else{
                        out.println("<input type=\"radio\" id=\"uar"+i+"\" name=\"userAnswer\" value=\""+answers.get(i)+"\">");
                    }
                    out.print("<label for=\"uar"+i+"\">"+answers.get(i)+"</label><br>");
                }
            }
        }else{
            out.println("<h4> question " + nextNum + "/" + quiz.getQuestions().size() + "</h4>");
            out.println("<h4> " + question.getBody() + "</h4>");
            if(previousAnswer == null){
                out.print("<textarea id=\"answerBody\" name=\"userAnswer\" rows=\"5\" " +
                        "cols=\"50\" placeholder=\"Fill in with your answer\"></textarea><br><br>");
            }else{
                out.print("<textarea id=\"answerBody\" name=\"userAnswer\" rows=\"5\" " +
                        "cols=\"50\">"+previousAnswer+"</textarea><br><br>");
            }
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
