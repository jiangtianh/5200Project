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
    <title>Find a Title</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Find a Title</h1>
    <form action="findtitle" method="post" class="mb-4">
        <div class="form-group">
            <label for="title">Title</label>
            <input id="title" name="title" type="text" class="form-control" value="${fn:escapeXml(param.title)}"/>
        </div>
        <input type="hidden" name="page" value="${param.page != null ? param.page : 1}"/>
        <button type="submit" class="btn btn-primary btn-block">Search</button>
        <div class="text-center mt-3">
            <span id="successMessage" class="text-success font-weight-bold">${messages}</span>
        </div>
    </form>
    <div class="text-center mb-4">
        <a href="index.jsp" class="btn btn-secondary">Back to Main</a>
    </div>

    <c:if test="${not empty titles}">
        <div class="table-responsive">
            <table class="table table-bordered">
                <thead class="thead-light">
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
                </thead>
                <tbody>
                <c:forEach var="title" items="${titles}">
                    <tr>
                        <td>
                            <a href="titledetail?titleId=${title.titleId}">
                                <c:out value="${title.titleId}"/>
                            </a>
                        </td>
                        <td><c:out value="${title.titleType}"/></td>
                        <td><c:out value="${title.primaryTitle}"/></td>
                        <td><c:out value="${title.originalTitle}"/></td>
                        <td><c:out value="${title.isAdult}"/></td>
                        <td><c:out value="${title.startYear}"/></td>
                        <td><c:out value="${title.endYear}"/></td>
                        <td><c:out value="${title.runtimeMinutes}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="findtitle?page=${currentPage - 1}&title=${fn:escapeXml(param.title)}">Previous</a>
                    </li>
                </c:if>
                <c:if test="${titles.size() == pageSize}">
                    <li class="page-item">
                        <a class="page-link" href="findtitle?page=${currentPage + 1}&title=${fn:escapeXml(param.title)}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>
    <c:if test="${empty titles}">
        <p class="text-center">No titles found with the given criteria.</p>
    </c:if>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>