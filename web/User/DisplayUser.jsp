<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>User Information</title>
    </head>
    <body>
        <h1>User Information</h1>
        <c:if test="${not empty user}">
            <p><b>Username:</b> <c:out value="${user.username}"/></p>
            <p><b>First Name:</b> <c:out value="${user.firstName}"/></p>
            <p><b>Last Name:</b> <c:out value="${user.lastName}"/></p>
            <p><b>Date of Birth:</b> <fmt:formatDate value="${user.dob}" pattern="yyyy-MM-dd"/></p>
            <p><b>MBTI:</b> <c:out value="${user.mbti}"/></p>
            <p><b>Profession:</b> <c:out value="${user.profession}"/></p>
        </c:if>
        <c:if test="${empty user}">
            <p>No user found with the given first name.</p>
        </c:if>
        <br/>
        <a href="FindUser.jsp">Search Again</a>
    </body>
</html>