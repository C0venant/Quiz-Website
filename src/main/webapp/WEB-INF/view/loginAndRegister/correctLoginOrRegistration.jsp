<%--suppress unchecked --%>
<%@ page import="com.quiz.model.quiz.Quiz" %>
<%@ page import="com.quiz.model.quiz.question.QuestionBasic" %>
<%@ page import="java.util.List" %>
<%@ page import="com.quiz.model.request.Request" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.quiz.model.user.UserCheck" %>

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
    <%
        List<Request> friendReqs = (List<Request>)request.getAttribute("friendRequests");
        List<Request> allUnreadMessages = (List<Request>)request.getAttribute("allUnreadMessages");
    %>

    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        .notification {
            background-color: #555;
            color: white;
            text-decoration: none;
            padding: 13px 23px;
            position: relative;
            display: inline-block;
            border-radius: 5px;
        }

        .notification:hover {
            background: red;
        }

        .notification .badge {
            position: absolute;
            top: -10px;
            right: -10px;
            padding: 5px 10px;
            border-radius: 50%;
            background-color: red;
            color: white;
        }
    </style>

    <form name="requestForm" action="friendRequests" class="notification" method="post">
        <span onclick="requestForm.submit()">Friend Requests</span>
        <input type="hidden" name="username" value=<%=request.getParameter("username")%>>
        <span class="badge"><%=friendReqs.size()%></span>
    </form>

    &nbsp;

    <form name="messengerForm" action=messenger class=notification method=post>
        <span onclick=messengerForm.submit()>Messenger</span>
        <input type=hidden name=username value=<%=request.getParameter("username")%>>
        <span class=badge><%=allUnreadMessages.size()%></span>
    </form>

    <form action="logout" method="post">
        <input type="submit" value="Logout">
    </form>


    <%
        Boolean isAdmin = (Boolean)request.getAttribute("isAdmin");
        if(isAdmin){
            out.print("<form action=\"listQuizAndUsers\" method=\"post\">");
            out.print("<input type=\"submit\" value=\"Admin options\">");
            out.print("<input type=\"hidden\" name=\"username\" value=\""+request.getParameter("username")+"\">");
            out.print("</form>");
        }
    %>

    <form action="createQuestion_basic" method="post">
        <input type="hidden" id="user" name="username" value=<%= request.getParameter("username")%>>
        <input type="submit" value="create Question">
    </form>

    <form action="chooseQuestions" method="post">
        <input type="hidden" id="user2" name="username" value=<%= request.getParameter("username")%>>
        <input type="submit" value="create Quiz">
    </form>

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

    <h4>Returned with mark: </h4>
    <table >
        <tr>
            <th>_____Quiz Name_____</th>
            <th>_____mark_____</th>
        </tr>
        <%
            Map<String, Integer> markedQuizzes = (Map<String, Integer>)request.getAttribute("isChecked");
            for(String s : markedQuizzes.keySet()){
                out.print("<tr><th>"+s+"</th><th>"+markedQuizzes.get(s)+"</th></tr>");
            }
        %>
    </table>



    <h4>Requires Checking: </h4>
    <table >
        <tr>
            <th>_____Quiz Name_____</th>
            <th>_____Username_____</th>
        </tr>
        <%
            List<UserCheck> checkList = (List<UserCheck>)request.getAttribute("needsChecking");
            for(UserCheck uc : checkList){
                out.print("<tr><th><span>&#9679;</span>"+"<a href=\"/quiz-trial/gradeQuiz?quizName="+uc.getQuizName()+
                        "&author="+request.getParameter("username")+"&username="+uc.getUsername()+"\">"+uc.getQuizName()+"</a></th>");
                out.print("<th>"+uc.getUsername()+"</th></tr>");
            }
        %>
    </table>

    <h4>Global quizzes: </h4>
    <table >
        <tr>
            <th>_____Quiz Name_____</th>
        </tr>
        <%
            List<String> globalQuizzes = (List<String>)request.getAttribute("globalQuizzes");
            for(String qn : globalQuizzes){
                out.print("<tr><th><span>&#9679;</span>"+"<a href=\"/quiz-trial/enrollQuiz?quizName="+qn+
                        "&username="+request.getParameter("username")+"\">"+qn+"</a></th></tr>");
            }
        %>
    </table>


</body>
</html>
