<%@ page import="java.util.List" %>
<%@ page import="com.quiz.model.quiz.question.QuestionBasic" %>
<%@ page import="com.quiz.model.quiz.question.utils.QuestionType" %>
<%@ page import="com.quiz.model.quiz.question.QuestionTest" %>
<%@ page import="com.quiz.model.quiz.question.QuestionFillBlank" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/26/2020
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Choose Question</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        .button {
            border: none;
            background-color: green;
            color: white;
            padding: 12px 16px;
            font-size: 16px;
            cursor: pointer;
        }

        .button:hover{
            background-color: darkgreen;
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
<form name="assembleForm" action="assembleQuestions" method="post">
<table class="content-table">
    <thead>
    <tr>
        <th>Choose Question</th>
    </tr>
    </thead>
    <tbody>
        <label for ="name"> Quiz Name: </label>
        <input type ="text" name="quizName" id="name" required><br><br>
        <%
            @SuppressWarnings("unchecked") List<QuestionBasic> list = (List<QuestionBasic>)request.getAttribute("questions");
            for (int i = 0; i < list.size(); i++){
                QuestionBasic q = list.get(i);
                if(q.getImageFile()!=null && !q.getImageFile().equals("")){
                    out.print("<tr><th><img src=\""+q.getImageFile()+"\" alt=\"image\" style=\"width:104px;height:142px;\">");
                }else{
                    out.print("<tr><th>");
                }
                if(q.getType().equals(QuestionType.TEST)){
                    QuestionTest qt = (QuestionTest)q;
                    out.println("<h4> max grade: "+qt.getMaxGrade()+"</h4>");
                    out.println("<h4>"+qt.getBody()+"</h4>");
                    for(int j = 0; j < qt.getAnswers().size();j++){
                        int index = j +1;
                        out.println("<h4>"+index+") "+qt.getAnswers().get(j)+"</h4>");
                    }
                }else if(q.getType().equals(QuestionType.BLANK)){
                    QuestionFillBlank qb = (QuestionFillBlank) q;
                    out.println("<h4> max grade: "+qb.getMaxGrade()+"</h4>");
                    out.println("<h4>"+qb.toString()+"</h4>");
                }else{
                    out.println("<h4> max grade: "+q.getMaxGrade()+"</h4>");
                    out.println("<h4>"+q.toString()+"</h4>");
                }
                out.print("<label for=\""+"question"+i+"\"> choose</label>");
                out.print("<input type=\"checkbox\""+"name=\""+"question"+i+"\" value=\""+q.getId()+"\"><br></th></tr>");
            }
        %>

    </tbody>
</table>
<input type="hidden" id="user" name="username" value=<%= request.getParameter("username")%>>
<button class="button" onclick="assembleForm.submit()"> Create quiz</button>
</form>
</body>
</html>
