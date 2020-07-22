<%--
  Created by IntelliJ IDEA.
  User: Irakli
  Date: 7/22/2020
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Information Incorrect</title>
</head>
<body>
    <h1>Please Try Again</h1>
    <p>Either your user name or password is incorrect. Please try again.</p>
    <form action="login" method="post">
        <label for ="uName"> User Name: </label>
        <input type ="text" name="username" id="uName" required><br><br>
        <label for="password"> Password:</label>
        <input type="password" name="password" id="password" required>
        <button type="submit">Submit</button><br>
        <p><a href="createAccount">Create New Account</a></p>
    </form>
</body>
</html>
