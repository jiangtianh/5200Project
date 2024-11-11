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
</head>
<body>
<h1>Create User</h1>
<form action="createuser" method="post">
    <p>
        <label for="username">UserName</label>
        <input id="username" name="username" value="">
    </p>
    <p>
        <label for="password">Password</label>
        <input id="password" name="password" value="">
    </p>
    <p>
        <label for="firstname">FirstName</label>
        <input id="firstname" name="firstname" value="">
    </p>
    <p>
        <label for="lastname">LastName</label>
        <input id="lastname" name="lastname" value="">
    </p>
    <p>
        <label for="dob">DoB (yyyy-mm-dd)</label>
        <input id="dob" name="dob" value="">
    </p>
    <p>
        <label for="mbti">MBTI</label>
        <input id="mbti" name="mbti" value="">
    </p>
    <p>
        <label for="profession">Profession</label>
        <select id="profession" name="profession">
            <c:forEach var="profession" items="${professions}">
                <option value="${profession}">${fn:replace(profession, '_', ' ')}</option>
            </c:forEach>
        </select>
    </p>
    <p>
        <input type="submit">
    </p>
</form>
<br/><br/>
<p>
    <span id="successMessage"><b>${messages}</b></span>
</p>
</body>
</html>
