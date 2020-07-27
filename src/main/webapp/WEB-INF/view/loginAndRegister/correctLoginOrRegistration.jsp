<%@ page import="com.quiz.model.quiz.Quiz" %>
<%@ page import="com.quiz.model.quiz.question.QuestionBasic" %>
<%@ page import="java.util.List" %>
<%@ page import="com.quiz.model.request.Request" %>
<%@ page import="com.quiz.database.UserDaoImplementation" %>
<%@ page import="com.quiz.model.user.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Irakli
  Date: 7/22/2020
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Welcome <%= request.getParameter("username")%>
    </title>
</head>
<body>
    <h1>Welcome <%= request.getParameter("username")%></h1>

    <form action="logout" method="post">
        <input type="submit" value="Logout">
    </form>

    <form action="sendFriendRequest" method="post">
        <input type="hidden" name="username" value=<%= request.getParameter("username")%>>
        <input type="submit" value="Send Friend Request">
    </form>

    <form action="createQuestion_basic" method="post">
        <input type="hidden" id="user" name="username" value=<%= request.getParameter("username")%>>
        <input type="submit" value="create Question">
    </form>

    <form action="chooseQuestions" method="post">
        <input type="hidden" id="user2" name="username" value=<%= request.getParameter("username")%>>
        <input type="submit" value="create Quiz">
    </form>

    <h4>Your friend requests:</h4><br>
    <table>
        <tr>
            <th>____Username____</th>
            <th>____First Name____</th>
            <th>____Last Name____</th>
            <th>____Accept/Reject</th>
        </tr>
        <%
            List<Request> friendReqs = (List<Request>)request.getAttribute("friendRequests");
            UserDaoImplementation userDao = (UserDaoImplementation)request.getAttribute("userDao");
            if(friendReqs != null){
                for(Request req : friendReqs){
                    User getUser = userDao.getUser(req.getFromUser());
                    out.print("<tr><th>"+req.getFromUser()+"</th>");
                    out.print("<th>"+getUser.getFirstName()+"</th>");
                    out.print("<th>"+getUser.getLastName()+"</th>");
                    out.print("<th><span>&#9679;</span>"+"<a href=\"/quiz-trial/acceptOrReject?fromUser="+req.getFromUser()+
                            "&toUser="+req.getToUser()+"&id="+ req.getId()+"\">"+"Yes/No"+"</a><th><tr>");
                }
            }
        %>
    </table>

    <h4>Your questions:</h4><br>
    <table >
        <tr>
            <th>_____Body_____</th>
            <th>_____Type_____</th>
            <th>_____Max Grade_____</th>
            <th>_____Correct Answer_____</th>
        </tr>
        <%
            List<QuestionBasic> list = (List<QuestionBasic>)request.getAttribute("questions");
            if(list != null){
                for(QuestionBasic q : list){
                    String shortView = " ";
                    if(q.getBody().length() > 20){
                        shortView+=q.getBody().substring(0, 20)+"...";
                    }else{
                        shortView+=q.getBody();
                    }
                    out.print("<tr><th><span>&#9679;</span>"+"<a href=\"/quiz-trial/editOrDeleteQuestion?id="+q.getId()+
                            "&username="+request.getParameter("username")+"\">"+shortView+"</a></th>");
                    out.print("<th>"+q.getType()+"</th>");
                    out.print("<th>"+q.getMaxGrade()+"</th>");
                    out.print("<th>"+q.getCorrectAnswer()+"</th></tr>");
                }
            }
        %>
    </table>

    <h4>Your Quizzes: </h4>
    <table >
        <tr>
            <th>_____Quiz Name_____</th>
            <th>_____Grade_____</th>
        </tr>

        <%
            List <Quiz> quizList = (List <Quiz>)request.getAttribute("quizzes");
            for(Quiz q : quizList){
                out.print("<tr><th><span>&#9679;</span>"+"<a href=\"/quiz-trial/viewQuiz?quizName="+q.getQuizName()+
                        "&username="+request.getParameter("username")+"\">"+q.getQuizName()+"</a></th>");
                out.print("<th>"+q.getOverallGrade()+"</th></tr>");
            }
        %>

    </table>

</body>
</html>
