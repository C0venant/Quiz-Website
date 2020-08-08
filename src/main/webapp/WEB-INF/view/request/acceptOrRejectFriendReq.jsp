<%--
  Created by IntelliJ IDEA.
  User: Quantori
  Date: 7/28/2020
  Time: 12:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Accept Or Reject</title>
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

        /* Darker background on mouse-over */
        .button1:hover {
            background-color: RoyalBlue;
        }

        .button2 {
            background-color: green;
        }

        .button3 {
            background-color: red;
        }

        .button4 {
            background-color: coral;
        }

        .button4:hover {
            background-color: lightcoral;
        }


    </style>
</head>
<body>
<form name="homepageForm" action="homepage" method="post">
    <button class="button button1" onclick="homepageForm.submit()"><i class="fa fa-home"></i> Homepage</button>
    <input type="hidden" name="username" value=<%=request.getParameter("toUser")%>>
</form>

<form name="friendRequests" action="friendRequests" method="post">
    <input type="hidden" name="username" value=<%=request.getParameter("toUser")%>>
    <button class="button button4" onclick="friendRequests.submit()"><i class="fa fa-home"></i> Friend Requests Main
        Page
    </button>
</form>

<h1>Are you sure you want to accept <%=request.getParameter("fromUser")%>'s friend request?</h1>

<form action="proceedAcceptOrReject" method="post">
    <input type="hidden" name="username" value=<%=request.getParameter("toUser")%>>
    <input type="hidden" id="fromuser" name="fromuser" value=<%=request.getParameter("fromUser")%>>
    <input type="hidden" name="id" value=<%=request.getParameter("id")%>>
    <input type="hidden" name="accept" value="yes">
    <button class="button button2" onclick="yesFunction()"><i class="fa fa-user-plus"></i> Yes</button>
</form>

<script>
    function yesFunction() {
        const fromUser = document.getElementById("fromuser").value;
        alert("You and " + fromUser + " are friends now");
    }

    function noFunction() {
        const fromUser = document.getElementById("fromuser").value;
        alert("You rejected " + fromUser + "'s friend request");
    }
</script>

<form action="proceedAcceptOrReject" method="post">
    <input type="hidden" name="username" value=<%=request.getParameter("toUser")%>>
    <input type="hidden" name="fromuser" value=<%=request.getParameter("fromUser")%>>
    <input type="hidden" name="id" value=<%=request.getParameter("id")%>>
    <input type="hidden" name="accept" value="no">
    <button class="button button3" onclick="noFunction()"><i class="fa fa-user-times"></i> No</button>
</form>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.1/js/bootstrap.min.js" integrity="sha384-XEerZL0cuoUbHE4nZReLT7nx9gQrQreJekYhJD9WNWhH8nEW+0c5qq7aIo2Wl30J" crossorigin="anonymous"></script>
</body>
</html>
