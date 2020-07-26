<%@ page import="java.util.List" %>
<%@ page import="com.quiz.model.quiz.question.QuestionBasic" %>
<%@ page import="java.util.ArrayList" %><%--
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
    <form action="addFriend" method="post">
        <input type="hidden" name="fromuser" value= <%= request.getParameter("username")%>>
        <label for="touser"> Enter friend's user name: </label>
        <input type="search" name="touser" id="touser">
        <input type="submit" value="Add friend">
    </form>

    <form action="logout" method="post">
        <input type="submit" value="Logout">
    </form>

    <form action="createQuestion_basic" method="post">
        <input type="hidden" id="user" name="username" value=<%= request.getParameter("username")%>>
        <input type="submit" value="create Question">
    </form>

    <form action="displayQuestions" method="post">
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

</body>
</html>
