<%--
  Created by IntelliJ IDEA.
  User: jiangtianhan
  Date: 11/10/24
  Time: 6:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<%
    HttpSession currentSession = request.getSession(false);
    if (currentSession == null || currentSession.getAttribute("loggedInUser") == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Find a User</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Find a User</h1>
    <form action="finduser" method="post" class="mb-4">
        <div class="form-group">
            <label for="username">Username</label>
            <input id="username" name="username" type="text" class="form-control" value="${fn:escapeXml(param.username)}"/>
        </div>
        <button type="submit" class="btn btn-primary btn-block">Search</button>
        <div class="text-center mt-3">
            <span id="successMessage" class="text-success font-weight-bold">${messages.success}</span>
        </div>
    </form>
    <div class="text-center mb-4">
        <a href="index.jsp" class="btn btn-secondary">Back to Main</a>
    </div>

    <h1 class="text-center">User Information</h1>
    <c:if test="${not empty user}">
        <div class="table-responsive">
            <table class="table table-bordered">
                <thead class="thead-light">
                <tr>
                    <th>Field</th>
                    <th>Value</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><b>Username:</b></td>
                    <td><c:out value="${user.username}"/></td>
                </tr>
                <tr>
                    <td><b>First Name:</b></td>
                    <td><c:out value="${user.firstName}"/></td>
                </tr>
                <tr>
                    <td><b>Last Name:</b></td>
                    <td><c:out value="${user.lastName}"/></td>
                </tr>
                <tr>
                    <td><b>Date of Birth:</b></td>
                    <td><fmt:formatDate value="${user.dob}" pattern="yyyy-MM-dd"/></td>
                </tr>
                <tr>
                    <td><b>MBTI:</b></td>
                    <td><c:out value="${user.mbti}"/></td>
                </tr>
                <tr>
                    <td><b>Profession:</b></td>
                    <td><c:out value="${user.profession}"/></td>
                </tr>
                </tbody>
            </table>
            <a href="reviewsbyuser?username=${user.username}">View User's Reviews</a>
        </div>
    </c:if>
    <c:if test="${empty user}">
        <p class="text-center">No user found with the given username.</p>
    </c:if>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>