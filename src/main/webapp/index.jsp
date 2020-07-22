<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Welcome</title>
</head>

<body>
<h1>Welcome to quiz website</h1>
<p>Please log in or create new account</p>

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
