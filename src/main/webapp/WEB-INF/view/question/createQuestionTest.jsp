<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 7/23/2020
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>Create Question</title>
</head>
<body>
<form action="registerQuestion" method="post">
    <input type="hidden" id="user" name="username" value=<%= request.getParameter("username")%>>
    <h4>Create your question: </h4><br>

    select type:
    <select id="questionType" name="type" onchange="location.href = 'http://localhost:8080/quiz-trial/createQuestion_'+this.value;">
        <option value="basic?username=${username}" selected>Basic</option>
        <option value="blank?username=${username}">Fill in blank</option>
        <option value="test?username=${username}" selected>Test</option>
    </select><br><br>

    <textarea id="questionBody" name="body" rows="5" cols="50" placeholder="Fill in" required></textarea><br><br>

    <label for="grade">Max Grade:</label>
    <input type="text" id="grade" name="maxGrade" value="0"><br><br>

    <label for="image">Image File:</label>
    <input type="text" id="image" name="imageFile" placeholder="optional"><br><br>

    <label for="answer">Correct Answer:</label>
    <input type="text" id="answer" name="correctAnswer"><br><br>

    <label for="answers">Probable Answers:</label><br>
    <h4>please use ',' between answers</h4>
    <textarea id="answers" name="answers" rows="1" cols="50" placeholder="enter probable answers" required></textarea><br><br>


    <input type="submit" value="create">
</form>

</body>
</html>