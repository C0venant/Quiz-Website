<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h2>Welcome to test homepage</h2><br>
<form action="test" method="post">
    <h4>please log in</h4><br>
    <label for ="un"> username:</label>
    <input type ="text" name="username" id="un" required><br>

    <label for="pw"> password:</label>
    <input type="text" name="password" id="pw" required><br>

    <button type="submit">Submit</button><br>
</form>
</body>
</html>
