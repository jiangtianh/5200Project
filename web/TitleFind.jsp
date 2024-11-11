<%--
  Created by IntelliJ IDEA.
  User: jiangtianhan
  Date: 11/11/24
  Time: 12:20 AM
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
    <title>Find a Title</title>
</head>
<body>

<h1>Find a Title</h1>
<form action="findtitle" method="post">
    <p>
        <label for="title">Title</label>
        <input id="title" name="title" type="text" value="${fn:escapeXml(param.title)}"/>
    </p>
    <input type="hidden" name="page" value="${param.page != null ? param.page : 1}"/>
    <p>
        <input type="submit">
        <br/><br/><br/>
        <span id="successMessage"><b>${messages}</b></span>
    </p>
</form>


<c:if test="${not empty titles}">
    <table border="1">
        <tr>
            <th>Title ID</th>
            <th>Title Type</th>
            <th>Primary Title</th>
            <th>Original Title</th>
            <th>Is Adult</th>
            <th>Start Year</th>
            <th>End Year</th>
            <th>Runtime Minutes</th>
        </tr>
        <c:forEach var="title" items="${titles}">
            <tr>
                <td><c:out value="${title.titleId}"/></td>
                <td><c:out value="${title.titleType}"/></td>
                <td><c:out value="${title.primaryTitle}"/></td>
                <td><c:out value="${title.originalTitle}"/></td>
                <td><c:out value="${title.isAdult}"/></td>
                <td><c:out value="${title.startYear}"/></td>
                <td><c:out value="${title.endYear}"/></td>
                <td><c:out value="${title.runtimeMinutes}"/></td>
            </tr>
        </c:forEach>
    </table>

    <div>
        <c:if test="${currentPage > 1}">
            <a href="findtitle?page=${currentPage - 1}&title=${fn:escapeXml(param.title)}">Previous</a>
        </c:if>
        <c:if test="${titles.size() == pageSize}">
            <a href="findtitle?page=${currentPage + 1}&title=${fn:escapeXml(param.title)}">Next</a>
        </c:if>
    </div>
</c:if>
<c:if test="${empty titles}">
    <p>No titles found with the given criteria.</p>
</c:if>








</body>
</html>
