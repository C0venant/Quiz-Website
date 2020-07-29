<%--suppress unchecked --%>
<%@ page import="com.quiz.model.request.Request" %>
<%@ page import="com.quiz.model.user.User" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.TreeMap" %><%--
  Created by IntelliJ IDEA.
  User: Quantori
  Date: 7/29/2020
  Time: 12:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <%
        User fromUser = (User)request.getAttribute("fromuser");
        String userName = request.getParameter("username");
        List<Request> receivedMessages = (List<Request>)request.getAttribute("receivedMessages");
        List<Request> sentMessages = (List<Request>)request.getAttribute("sentMessages");
    %>
<head>
    <title>Messenger | <%=fromUser.getFirstName()%> <%=fromUser.getLastName()%></title>
</head>
<body>
    <form action="homepage" method="post">
        <input type="hidden" name="username" value=<%=userName%>>
        <input type="submit" value="Homepage">
    </form>

    <form action="messenger" method="post">
        <input type="hidden" name="username" value=<%=userName%>>
        <input type="submit" value="Messenger Main Page">
    </form>

        <style>
            .section{
                max-height: 400px;
                overflow-y: auto;
                margin: 32px 64px;
                display: flex;
                flex-direction: column-reverse;
            }

            .container {
                border: 2px solid #dedede;
                background-color: #f1f1f1;
                border-radius: 5px;
                padding: 10px;
                margin: 10px 0;
            }

            .darker {
                border-color: #ccc;
                background-color: #ddd;
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
                margin-right:0;
            }
        </style>

    <h2>Chat Messages</h2>

    <div class="section">
        <div class="messenger">

        <%
            Map<Request, String> map = new HashMap<Request, String>();

            for(Request req : receivedMessages){
                map.put(req, "received");
            }
            for(Request req : sentMessages){
                map.put(req, "sent");
            }

            TreeMap<Request, String> sorted = new TreeMap<Request, String>(map);

            for(Request res : sorted.keySet()){
                if(sorted.get(res).equals("received")){
                    out.print("<div class=\"container\">\n" +
                            "        <text style=\"width:100%;\"> "+ fromUser.getFirstName() + "</text>\n" +
                            "        <p>" + res.getBody() + "</p>\n" +
                            "    </div>");

                }
                if(sorted.get(res).equals("sent")){
                    out.print("<div class=\"container darker\">\n" +
                            "        <text class=\"right\" style=\"width:100%;\">Me</text>\n" +
                            "        <p>" + res.getBody() + "</p>\n" +
                            "    </div>");
                }
            }

        %>

        </div>
    </div>


</body>
</html>
