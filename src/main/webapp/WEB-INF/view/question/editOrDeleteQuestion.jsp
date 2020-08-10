<%@ page import="com.quiz.model.quiz.question.QuestionBasic" %>
<%@ page import="com.quiz.model.quiz.question.QuestionTest" %>
<%@ page import="com.quiz.model.quiz.question.utils.QuestionType" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/24/2020
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>edit question</title>
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

        /* Darker background on mouse-over */
        .button:hover {
            background-color: RoyalBlue;
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

<form name="homepageForm" action="homepage" method="post">
    <button class="button" onclick="homepageForm.submit()"><i class="fa fa-home"></i> Homepage</button>
    <input type="hidden" name="username" value=<%=request.getParameter("username")%>>
</form>

<table class="content-table">
    <thead>
    <tr>
        <th>Test Question</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th>
            <form action="editQuestion" method="post">
                <input type="hidden" id="user" name="username" value=<%= request.getParameter("username")%>>
                <input type="hidden" id="id" name="id" value=<%= request.getParameter("id")%>>
                <%
                    QuestionBasic q = (QuestionBasic) request.getAttribute("question");
                    out.print("<h4>Question Type: " + q.getType() + "</h4>");
                    out.print("<input type=\"hidden\" id=\"t\" name=\"type\" value=" + q.getType() + ">");
                    out.print("<textarea id=\"questionBody\" name=\"body\" rows=\"5\" cols=\"50\" placeholder=\"Fill in\" required>");
                    out.print(q.getBody() + "</textarea><br><br>");
                    out.print("<label for=\"grade\">Max Grade:</label>");
                    out.print("<input type=\"text\" id=\"grade\" name=\"maxGrade\" value=\"" + q.getMaxGrade() + "\"><br><br>");
                    out.print("<label for=\"image\">Image File:</label>");
                    out.print("<input type=\"text\" id=\"image\" name=\"imageFile\" value=\"" + q.getImageFile() + "\"><br><br>");
                    if (q.getType().equals(QuestionType.BLANK)) {
                        out.print("<label for=\"answer\">Correct Answer:</label>");
                        out.print("<input type=\"text\" id=\"answer\" name=\"correctAnswer\" value=\"" + q.getCorrectAnswer() + "\"><br><br>");
                    } else if (q.getType().equals(QuestionType.TEST)) {
                        q = (QuestionTest) q;
                        out.print("<label for=\"answer\">Correct Answer:</label>");
                        out.print("<input type=\"text\" id=\"answer\" name=\"correctAnswer\" value=\"" + q.getCorrectAnswer() + "\"><br><br>");
                        out.print("<label for=\"answers\">Probable Answers:</label><br>" + "<h4>please use ',' between answers</h4>");
                        out.print("<textarea id=\"answers\" name=\"answers\" rows=\"1\" cols=\"50\" required>");
                        for (String answer : ((QuestionTest) q).getAnswers()) {
                            out.print(answer + ",");
                        }
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
        </th>
    </tr>
    </tbody>
</table>
</body>
</html>
