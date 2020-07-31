<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/31/2020
  Time: 00:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>info list</title>
</head>
<body>

<form action="returnHome" method="post">
    <input type=hidden name=username value=<%=request.getParameter("username")%>>
    <input type="submit" value="Home">
</form>

<table >
    <tr>
        <th>_____Users_____</th>

    </tr>
    <%
        List<String> userList = (List<String>)request.getAttribute("userList");
        for(String s : userList){
            out.print("<tr><th><span>&#9679;</span>"+"<a href=\"/quiz-trial/loadUserView?globalUser="+s+
                    "&username="+request.getParameter("username")+"\">"+s+"</a></th></tr>");
        }

    %>
</table>

<table >
    <tr>
        <th>_____Quizzes_____</th>
    </tr>
    <%
        List<String> quizList = (List<String>)request.getAttribute("quizList");
        for(String s : quizList){
            out.print("<tr><th><span>&#9679;</span>"+"<a href=\"/quiz-trial/loadQuizView?quizName="+s+
                    "&username="+request.getParameter("username")+"\">"+s+"</a></th></tr>");
        }
    %>
</table>

</body>
</html>
