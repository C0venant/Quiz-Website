<%@ page import="com.quiz.database.UserDaoImplementation" %>
<%@ page import="com.quiz.model.request.Request" %>
<%@ page import="java.util.List" %>
<%@ page import="com.quiz.model.user.User" %><%--
  Created by IntelliJ IDEA.
  User: Quantori
  Date: 7/28/2020
  Time: 3:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Friend requests</title>
</head>
<body>
    <form action="homepage" method="post">
        <input type="hidden" name="username" value=<%=request.getParameter("username")%>>
        <input type="submit" value="Homepage">
    </form>

    <form action="proceedSendFriendRequest" method="post">
        <h1>Enter friend's user name</h1>
        <input type="hidden" id="user" name="username" value=<%= request.getParameter("username")%>>
        <label for="touser"> Enter name here: </label>
        <input type="text" name="touser" id="touser" required>
        <input type="submit" value="Add friend">
    </form>

    <h4>Your friend requests:</h4><br>
    <table>
        <tr>
            <th>____Username____</th>
            <th>____First Name____</th>
            <th>____Last Name____</th>
            <th>____Accept/Reject____</th>
        </tr>
        <%
            @SuppressWarnings("unchecked") List<Request> friendReqs = (List<Request>)request.getAttribute("friendRequests");
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

</body>
</html>
