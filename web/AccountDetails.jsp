<%--
  Created by IntelliJ IDEA.
  User: jiangtianhan
  Date: 11/12/24
  Time: 10:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
  <title>Account Details</title>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h1 class="text-center">Account Details</h1>
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
    </div>
  </c:if>
  <c:if test="${empty user}">
    <p class="text-center">No user information available.</p>
  </c:if>

  <a href="updateaccountdetails">Update Account Details</a>
  <div class="text-center mb-4">
    <a href="index.jsp" class="btn btn-secondary">Back to Main</a>
  </div>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>