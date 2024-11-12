<%--
  Created by IntelliJ IDEA.
  User: jiangtianhan
  Date: 11/10/24
  Time: 9:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Create a User</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Create User</h1>

    <div class="text-center">
        <span id="successMessage" class="text-success font-weight-bold">${messages}</span>
    </div>

    <form action="createuser" method="post" class="mb-4">
        <div class="form-group">
            <label for="username">Username</label>
            <input id="username" name="username" type="text" class="form-control" value="">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input id="password" name="password" type="password" class="form-control" value="">
        </div>
        <div class="form-group">
            <label for="firstname">First Name</label>
            <input id="firstname" name="firstname" type="text" class="form-control" value="">
        </div>
        <div class="form-group">
            <label for="lastname">Last Name</label>
            <input id="lastname" name="lastname" type="text" class="form-control" value="">
        </div>
        <div class="form-group">
            <label for="dob">Date of Birth (yyyy-mm-dd)</label>
            <input id="dob" name="dob" type="text" class="form-control" value="">
        </div>
        <div class="form-group">
            <label for="mbti">MBTI</label>
            <input id="mbti" name="mbti" type="text" class="form-control" value="">
        </div>
        <div class="form-group">
            <label for="profession">Profession</label>
            <select id="profession" name="profession" class="form-control">
                <c:forEach var="profession" items="${professions}">
                    <option value="${profession}">${fn:replace(profession, '_', ' ')}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary btn-block">Create</button>
    </form>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>