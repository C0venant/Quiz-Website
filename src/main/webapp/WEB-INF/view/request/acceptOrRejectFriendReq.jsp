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
</head>
<body>
    <form action="homepage" method="post">
        <input type="hidden" name="username" value=<%=request.getParameter("toUser")%>>
        <input type="submit" value="Homepage">
    </form>

    <h1>Are you sure you want to accept <%=request.getParameter("fromUser")%>'s friend request?</h1>

    <form action="proceedAcceptOrReject" method="post">
        <input type="hidden" name="username" value=<%=request.getParameter("toUser")%>>
        <input type="hidden" name="fromuser" value=<%=request.getParameter("fromUser")%>>
        <input type="hidden" name="id" value=<%=request.getParameter("id")%>>
        <input type="hidden" name="accept" value="yes">
        <input type="submit" value="Yes">
    </form>

    <form action="proceedAcceptOrReject" method="post">
        <input type="hidden" name="username" value=<%=request.getParameter("toUser")%>>
        <input type="hidden" name="fromuser" value=<%=request.getParameter("fromUser")%>>
        <input type="hidden" name="id" value=<%=request.getParameter("id")%>>
        <input type="hidden" name="accept" value="no">
        <input type="submit" value="No">
    </form>

</body>
</html>
