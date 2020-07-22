<%--
  Created by IntelliJ IDEA.
  User: Irakli
  Date: 7/22/2020
  Time: 7:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create Account</title>
</head>
<body>
    <h1>The user name <%= request.getParameter("username") %> is Already In Use</h1>
    <p>Please enter another user name.</p>
    <p>Also enter first and last names if you want.</p>

    <form action="proceedAccountCreation" method="post">
        <label for="uName">User Name:</label>
        <input type="text" id="uName" name="username" value="" required><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="" required><br><br>
        <label for="fName"> First Name:</label>
        <input type="text" id="fName" name="firstname" value=""><br><br>
        <label for="lName"> Last Name:</label>
        <input type="text" id="lName" name="lastname" value="">
        <input type="submit" value="Login">
    </form>
</body>
</html>
