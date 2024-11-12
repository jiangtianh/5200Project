<%--
  Created by IntelliJ IDEA.
  User: jiangtianhan
  Date: 11/12/24
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
  <title>Update Account Details</title>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h1 class="text-center">Update Account Details</h1>
  <form action="updateaccountdetails" method="post">

    <div class="form-group">
      <label for="password">Password:</label>
      <input type="password" class="form-control" id="password" name="password" value="${user.password}" required>
    </div>

    <div class="form-group">
      <label for="firstName">First Name:</label>
      <input type="text" class="form-control" id="firstName" name="firstName" value="${user.firstName}" required>
    </div>
    <div class="form-group">
      <label for="lastName">Last Name:</label>
      <input type="text" class="form-control" id="lastName" name="lastName" value="${user.lastName}" required>
    </div>
    <div class="form-group">
      <label for="dob">Date of Birth:</label>
      <input type="date" class="form-control" id="dob" name="dob" value="${user.dob}" required>
    </div>
    <div class="form-group">
      <label for="mbti">MBTI:</label>
      <input type="text" class="form-control" id="mbti" name="mbti" value="${user.mbti}" required>
    </div>
    <div class="form-group">
      <label for="profession">Profession:</label>
      <select class="form-control" id="profession" name="profession" required>
        <c:forEach var="profession" items="${professions}">
          <option value="${profession}" <c:if test="${profession == user.profession}">selected</c:if>>${fn:replace(profession, '_', ' ')}</option>
        </c:forEach>
      </select>
    </div>
    <button type="submit" class="btn btn-primary">Update</button>
  </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>