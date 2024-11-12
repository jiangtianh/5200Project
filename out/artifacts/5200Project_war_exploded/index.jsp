<%--
  Created by IntelliJ IDEA.
  User: jiangtianhan
  Date: 11/10/24
  Time: 11:55 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  HttpSession currentSession = request.getSession(false);
  if (currentSession == null || currentSession.getAttribute("loggedInUser") == null) {
    response.sendRedirect("Login.jsp");
    return;
  }
%>


<!DOCTYPE html>
<html>
<head>
  <title>CineMatch</title>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h1 class="text-center">CineMatch</h1>

  <div class="text-center mt-4">
    <a href="UserFind.jsp" class="btn btn-primary">Find User</a>
    <br/>
    <a href="TitleFind.jsp" class="btn btn-primary mt-2">Find Title</a>
    <br/>
    <a href="PersonFind.jsp" class="btn btn-primary mt-2">Find Person</a>
    <br/>
    <a href="accountdetails" class="btn btn-primary mt-2">Account Details</a>
    <br/>
    <a href="logout" class="btn btn-danger mt-4">Logout</a>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>