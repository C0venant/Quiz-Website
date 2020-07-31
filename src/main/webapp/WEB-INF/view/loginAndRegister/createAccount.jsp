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

    <button onclick="loginPage()">Login page</button>
    <h1>Create New Account</h1>
    <p>Please enter proposed user name and password.</p>
    <p>Also enter first and last names.</p>

    <form action="proceedAccountCreation" method="post">
        <label for="uName">User Name:</label>
        <input type="text" id="uName" name="username" value="" required><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="" required><br><br>
        <label for="fName"> First Name:</label>
        <input type="text" id="fName" name="firstname" value="" required><br><br>
        <label for="lName"> Last Name:</label>
        <input type="text" id="lName" name="lastname" value="" required>
        <input type="submit" value="Create account">
    </form>
</body>
</html>
