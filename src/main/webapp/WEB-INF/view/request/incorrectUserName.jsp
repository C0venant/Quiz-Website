<%--
  Created by IntelliJ IDEA.
  User: Irakli
  Date: 7/26/2020
  Time: 9:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Send friend request</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .button {
            border: none;
            color: white;
            padding: 12px 16px;
            font-size: 16px;
            cursor: pointer;
        }

        .button1 {
            background-color: DodgerBlue;
        }

        .button2 {
            background-color: coral;
        }

        /* Darker background on mouse-over */
        .button1:hover {
            background-color: RoyalBlue;
        }

        .button2:hover {
            background-color: lightcoral;
        }

        textarea {
            width: 40%;
            height: 50px;
            padding: 12px 20px;
            box-sizing: border-box;
            border: 2px solid #ccc;
            border-radius: 4px;
            background-color: #f8f8f8;
            font-size: 16px;
            resize: none;
        }

        input[type=button], input[type=submit], input[type=reset] {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 16px 32px;
            text-decoration: none;
            margin: 4px 2px;
            cursor: pointer;
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
<body>
<form name="homepageForm" action="homepage" method="post">
    <button class="button button1" onclick="homepageForm.submit()"><i class="fa fa-home"></i> Homepage</button>
    <input type="hidden" name="username" value=<%=request.getParameter("username")%>>
</form>

<form name="friendRequests" action="friendRequests" method="post">
    <input type="hidden" name="username" value=<%=request.getParameter("username")%>>
    <button class="button button2" onclick="friendRequests.submit()"><i class="fa fa-home"></i> Friend Requests Main
        Page
    </button>
</form>

<table class="content-table">
    <thead>
    <tr>
        <th>Incorrect user name</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th>
            <form name="proceedForm" action="proceedSendFriendRequest" method="post">
                <h1>Incorrect user name <%=request.getParameter("touser")%>. Enter Another:</h1>
                <input type="hidden" id="user" name="username" value=<%= request.getParameter("username")%>>
                <label for="touser"></label><textarea rows="5" cols="100" name="touser" id="touser" required></textarea><br><br>
                <input type="button" onclick="proceedForm.submit()" value="Add friend">
            </form>
        </th>
    </tr>
    </tbody>
</table>
</body>
</html>
