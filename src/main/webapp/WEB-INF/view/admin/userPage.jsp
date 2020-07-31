<%@ page import="com.quiz.model.user.User" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/31/2020
  Time: 01:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
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

    <table class="content-table">
        <thead>
        <tr>
            <th>User Info</th>
        </tr>
        </thead>
        <tbody>
        <tr><th>
            <form action="deleteUserFromSite" method="post">
    <%
        User user = (User) request.getAttribute("user");
        out.println("<h4> loginName: "+user.getLoginName()+"</h4>");
        out.println("<h4> firstName: "+user.getFirstName()+"</h4>");
        out.println("<h4> lastName: "+user.getLastName() +"</h4>");
        out.print("<input type=\"hidden\" id=\"gu1\" name=\"globalUser\" value=\""+user.getLoginName()+"\">");
    %>
            <input type="hidden" id="user2" name="username" value=<%= request.getParameter("username")%>>
            <input type="submit" value="delete">
            </form>
            <form action="grantAdmin" method="post">
                <%
                    User user1 = (User) request.getAttribute("user");
                    out.print("<input type=\"hidden\" id=\"gu2\" name=\"globalUser\" value=\""+user1.getLoginName()+"\">");
                %>
                <input type="hidden" id="user" name="username" value=<%= request.getParameter("username")%>>
                <input type="submit" value="Grant Admin">
            </form>
        </th></tr>
        </tbody>
    </table>





</body>
</html>
