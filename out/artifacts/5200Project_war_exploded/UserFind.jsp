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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Find a User</title>
</head>
<body>
<h1>Find a User</h1>
<form action="finduser" method="post">
    <p>
        <label for="username">Username</label>
        <input id="username" name="username" type="text" value="${fn:escapeXml(param.username)}"/>
    </p>
    <p>
        <input type="submit">
        <br/><br/><br/>
        <span id="successMessage"><b>${messages.success}</b></span>
    </p>
</form>
<br/>
<a href="createuser">Create a User</a>

<h1>User Information</h1>
<c:if test="${not empty user}">
    <table>
        <tr>
            <th>Field</th>
            <th>Value</th>
        </tr>
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
    </table>
</c:if>
<c:if test="${empty user}">
    <p>No user found with the given username.</p>
</c:if>
<br/>



</body>
</html>