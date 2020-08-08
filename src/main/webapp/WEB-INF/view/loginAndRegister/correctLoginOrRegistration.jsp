<%--suppress unchecked --%>
<%@ page import="com.quiz.model.quiz.Quiz" %>
<%@ page import="com.quiz.model.quiz.question.QuestionBasic" %>
<%@ page import="com.quiz.model.request.Request" %>
<%@ page import="com.quiz.model.user.UserCheck" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.1/css/bootstrap.min.css" integrity="sha384-VCmXjywReHh4PwowAiWNagnWcLhlEJLA5buUprzK8rxFgeH0kww/aWY76TfkUoSX" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Welcome <%= request.getParameter("username")%>
    </title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        * {
            box-sizing: border-box;
        }

        .row {
            display: flex;
        }

        .column {
            flex: 50%;
            padding: 10px;
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

        .content-table {
            table-layout: auto;
            width: 100%;
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
            text-align: center;
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
<div class = "container">
<body onload="determineIfQuestionCreated()">
<%
    String somethingChanged = (String) request.getAttribute("somethingChanged");
%>
<input type="hidden" id="somethingChanged" name="somethingChanged" value=<%=somethingChanged%>>
<input type="hidden" id="username" name="username" value=<%=request.getParameter("username")%>>

<script>
    function determineIfQuestionCreated() {
        const questionCreated = document.getElementById("somethingChanged").value;
        const userName = document.getElementById("username").value;
        if (questionCreated === "true") {
            window.location.href = "/quiz-trial/homepage?username=" + userName;
        }
    }
</script>

<div class="row">
    <div class="column">
        <h1>Welcome <var> <%= request.getParameter("username")%> <var/>
        </h1>
        <%
            List<Request> friendReqs = (List<Request>) request.getAttribute("friendRequests");
            List<Request> allUnreadMessages = (List<Request>) request.getAttribute("allUnreadMessages");
        %>

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

        <form name="logoutForm" action="logout" method="post">
            <button class="btn btn-danger" onclick="logoutForm.submit()">
                <i class="fa fa-sign-in" aria-hidden="true"></i>Logout</button>
        </form>


        <%
            Boolean isAdmin = (Boolean) request.getAttribute("isAdmin");
            if (isAdmin) {
                out.print("<form action=\"listQuizAndUsers\" method=\"post\">");
                out.print("<input type=\"submit\" value=\"Admin options\">");
                out.print("<input type=\"hidden\" name=\"username\" value=\"" + request.getParameter("username") + "\">");
                out.print("</form>");
            }
        %>

        <form action="createQuestion_basic" method="post">
            <button class="btn btn-primary" onclick="let createQuestion_basic;
            createQuestion_basic.submit()">
            <i class="fa fa-sign-in" aria-hidden="true"></i>create Question</button>
            <input type="hidden" id="user" name="username" value=<%= request.getParameter("username")%>>
        </form>



        <form action="chooseQuestions" method="post">
            <input type="hidden" id="user2" name="username" value=<%= request.getParameter("username")%>>
            <button class="btn btn-info" onclick="let chooseQuestions;
            chooseQuestions.submit()">
                <i class="fa fa-sign-in" aria-hidden="true"></i>create Quiz</button>
        </form>

        <h4><var>Your questions:</var></h4>
        <table class="content-table">
            <thead>
            <tr>
                <th>Body</th>
                <th>Type</th>
                <th>Max Grade</th>
                <th>Correct Answer</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<QuestionBasic> list = (List<QuestionBasic>) request.getAttribute("questions");
                if (list != null) {
                    for (QuestionBasic q : list) {
                        String shortView = " ";
                        if (q.getBody().length() > 9) {
                            shortView += q.getBody().substring(0, 9) + "...";
                        } else {
                            shortView += q.getBody();
                        }
                        out.print("<tr><th><span>&#9679;</span>" + "<a href=\"/quiz-trial/editOrDeleteQuestion?id=" + q.getId() +
                                "&username=" + request.getParameter("username") + "\">" + shortView + "</a></th>");
                        out.print("<th>" + q.getType() + "</th>");
                        out.print("<th>" + q.getMaxGrade() + "</th>");
                        if (q.getCorrectAnswer() != null) {
                            out.print("<th>" + q.getCorrectAnswer() + "</th></tr>");
                        } else {
                            out.print("<th></th></tr>");
                        }

                    }
                }
            %>
            </tbody>
        </table>
    </div>
    <div class="column">
        <h4><var>Your Quizzes:</var></h4>
        <table class="content-table">
            <thead>
            <tr>
                <th>Quiz Name</th>
                <th>Grade</th>
            </tr>
            </thead>
            <tbody>

            <%
                List<Quiz> quizList = (List<Quiz>) request.getAttribute("quizzes");
                for (Quiz q : quizList) {
                    out.print("<tr><th><span>&#9679;</span>" + "<a href=\"/quiz-trial/viewQuiz?quizName=" + q.getQuizName() +
                            "&username=" + request.getParameter("username") + "\">" + q.getQuizName() + "</a></th>");
                    out.print("<th>" + q.getOverallGrade() + "</th></tr>");
                }
            %>
            </tbody>
        </table>

        <h4><var>Returned with mark:</var> </h4>
        <table class="content-table">
            <thead>
            <tr>
                <th>Quiz Name</th>
                <th>mark</th>
            </tr>
            </thead>
            <tbody>
            <%
                Map<String, Integer> markedQuizzes = (Map<String, Integer>) request.getAttribute("isChecked");
                for (String s : markedQuizzes.keySet()) {
                    out.print("<tr><th>" + s + "</th><th>" + markedQuizzes.get(s) + "</th></tr>");
                }
            %>
            </tbody>
        </table>
        <h4><var>Requires Checking:</var> </h4>
        <table class="content-table">
            <thead>
            <tr>
                <th>Quiz Name</th>
                <th>Username</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<UserCheck> checkList = (List<UserCheck>) request.getAttribute("needsChecking");
                for (UserCheck uc : checkList) {
                    out.print("<tr><th><span>&#9679;</span>" + "<a href=\"/quiz-trial/gradeQuiz?quizName=" + uc.getQuizName() +
                            "&username=" + request.getParameter("username") + "&checkReq=" + uc.getUsername() + "\">" + uc.getQuizName() + "</a></th>");
                    out.print("<th>" + uc.getUsername() + "</th></tr>");
                }
            %>
            </tbody>
        </table>

        <h4><var>Global quizzes:</var> </h4>
        <table class="content-table">
            <thead>
            <tr>
                <th>Quiz Name</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<String> globalQuizzes = (List<String>) request.getAttribute("globalQuizzes");
                for (String qn : globalQuizzes) {
                    out.print("<tr><th><span>&#9679;</span>" + "<a href=\"/quiz-trial/enrollQuiz?quizName=" + qn +
                            "&username=" + request.getParameter("username") + "\">" + qn + "</a></th></tr>");
                }
            %>
            </tbody>
        </table>
    </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.1/js/bootstrap.min.js" integrity="sha384-XEerZL0cuoUbHE4nZReLT7nx9gQrQreJekYhJD9WNWhH8nEW+0c5qq7aIo2Wl30J" crossorigin="anonymous"></script>
</body>
</html>
