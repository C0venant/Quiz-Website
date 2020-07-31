<%--
  Created by IntelliJ IDEA.
  User: Irakli
  Date: 7/22/2020
  Time: 6:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create Account</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }
        .content-table {
            border-collapse: collapse;
            margin-left:auto;
            margin-right:auto;
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
<body onload="determineIfCreated()">
    <%
        String created = (String)request.getAttribute("created");
    %>
    <input type="hidden" id="created" name="created" value=<%=created%>>
    <input type="hidden" id="username" name="username" value=<%=request.getParameter("username")%>>

    <script>
        function loginPage() {
            window.location.href = "/quiz-trial";
        }

        function determineIfCreated() {
            const userName = document.getElementById("username").value;
            const create = document.getElementById("created").value;
            if(create === "true"){
                alert("Account with username: '" + userName + "' created");
                window.location.href = "/quiz-trial/createAccount";
            }
        }
    </script>

    <form action="proceedAccountCreation" method="post">
        <table class="content-table" >
            <thead>
            <tr>
                <th>Create New Account</th>
            </tr>
            </thead>
            <tbody>
            <tr><th>
                <p>Please enter proposed user name and password.</p>
                <p>Also enter first and last names.</p>
                <label for="uName">User Name:</label>
                <input type="text" id="uName" name="username" value="" required><br><br>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" value="" required><br><br>
                <label for="fName"> First Name:</label>
                <input type="text" id="fName" name="firstname" value="" required><br><br>
                <label for="lName"> Last Name:</label>
                <input type="text" id="lName" name="lastname" value="" required><br><br>
                <button onclick="loginPage()">Login page</button>
                <input type="submit" value="Create account">
            </th></tr>
            </tbody>
        </table>
    </form>
</body>
</html>
