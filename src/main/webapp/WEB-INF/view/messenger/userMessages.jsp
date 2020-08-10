<%--suppress ALL --%>
<%--suppress Convert2Diamond --%>
<%--suppress unchecked --%>
<%--suppress Convert2Diamond --%>
<%--suppress unchecked --%>
<%@ page import="com.quiz.model.request.Request" %>
<%@ page import="com.quiz.model.user.User" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.TreeMap" %>
<%--
  Created by IntelliJ IDEA.
  User: Quantori
  Date: 7/29/2020
  Time: 12:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<%
    User fromUser = (User) request.getAttribute("fromuser");
    String userName = request.getParameter("username");
    List<Request> receivedMessages = (List<Request>) request.getAttribute("receivedMessages");
    List<Request> sentMessages = (List<Request>) request.getAttribute("sentMessages");
%>
<head>
    <title>Messenger | <%=fromUser.getFirstName()%> <%=fromUser.getLastName()%>
    </title>

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

        .section {
            max-height: 400px;
            overflow-y: auto;
            margin: 32px 64px;
            display: flex;
            flex-direction: column-reverse;
        }

        .sendMessage {
            margin: 8px 64px;
        }

        .container {
            border: 2px solid #dedede;
            background-color: #f1f1f1;
            border-radius: 5px;
            padding: 10px;
            margin: 10px 0;
        }

        .darker {
            border-color: coral;
            background-color: coral;
        }

        .container::after {
            content: "";
            clear: both;
            display: table;
        }

        .container text {
            float: left;
            max-width: 60px;
            width: 100%;
            margin-right: 20px;
            border-radius: 50%;
        }

        .container text.right {
            float: right;
            margin-left: 20px;
            margin-right: 0;
        }

    </style>
</head>
<body>
<form name="homepageForm" action="homepage" method="post">
    <button class="button button1" onclick="homepageForm.submit()"><i class="fa fa-home"></i> Homepage</button>
    <input type="hidden" name="username" value=<%=userName%>>
</form>

<form name="friendRequests" action="messenger" method="post">
    <input type="hidden" name="username" value=<%=userName%>>
    <button class="button button2" onclick="friendRequests.submit()"><i class="fa fa-home"></i> Messenger Main
        Page
    </button>
</form>

<h2>Chat Messages</h2>

<div class="section">
    <div class="messenger">

        <%
            Map<Request, String> map = new HashMap<Request, String>();

            for (Request req : receivedMessages) {
                map.put(req, "received");
            }
            for (Request req : sentMessages) {
                map.put(req, "sent");
            }

            TreeMap<Request, String> sorted = new TreeMap<Request, String>(map);

            for (Request res : sorted.keySet()) {
                if (sorted.get(res).equals("received")) {
                    out.print("<div class=\"container\">\n" +
                            "        <text style=\"width:100%;\"> " + fromUser.getFirstName() + "</text>\n" +
                            "        <p>" + res.getBody() + "</p>\n" +
                            "    </div>");

                }
                if (sorted.get(res).equals("sent")) {
                    out.print("<div class=\"container darker\">\n" +
                            "        <text class=\"right\" style=\"width:100%;\">Me</text>\n" +
                            "        <p>" + res.getBody() + "</p>\n" +
                            "    </div>");
                }
            }

        %>
    </div>
</div>

&nbsp;&nbsp;&nbsp;&nbsp;
<div class="sendMessage">
    <input type="hidden" id="touser" name="touser" value=<%=fromUser.getLoginName()%>>
    <input type="hidden" id="username" name="username" value=<%=userName%>>
    <label for="messageText">Text: </label><textarea id="messageText" name="messageText"></textarea>
    <button class="button button1" onclick="sendFunction()"><i class="fa fa-paper-plane"></i> Send Messsage</button>
</div>


<script>
    function sendFunction() {
        const userName = document.getElementById("username").value;
        const toUser = document.getElementById("touser").value;
        const messageText = document.getElementById("messageText").value;
        if (!messageText) return;
        fetch("/quiz-trial/sendMessage?username=" + userName + "&touser=" + toUser + "&messageText=" + messageText)
            .then((e) => console.log(e)).catch(e => console.error(e));
        location.reload();
    }
</script>


</body>
</html>
