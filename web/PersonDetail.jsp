<%--
  Created by IntelliJ IDEA.
  User: jiangtianhan
  Date: 11/11/24
  Time: 7:55 PM
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
    <title>Person Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .container {
            max-width: 600px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .title {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .detail {
            margin-bottom: 10px;
        }
        .label {
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="title">Person Details</div>
    <div class="detail">
        <span class="label">Name:</span>
        <span>${person.name}</span>
    </div>
    <div class="detail">
        <span class="label">Birth Year:</span>
        <span>${person.birthYear}</span>
    </div>
    <div class="detail">
        <span class="label">Death Year:</span>
        <span>${person.deathYear}</span>
    </div>
    <div class="detail">
        <span class="label">Profession:</span>
        <span>${person.primaryProfession}</span>
    </div>
    <br/><br/><br/>
    <div class="title">Famous Titles</div>
    <c:forEach var="title" items="${famousTitles}">
        <div class="detail">
            <span class="label">Title:</span>
            <span>
                <a href="titledetail?titleId=${title.titleId}">
                    ${title.primaryTitle}
                </a>
            </span>
        </div>
    </c:forEach>

</div>


</body>
</html>
