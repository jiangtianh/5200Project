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
    <form action="findtitle" method="get" class="mb-4">
        <div class="form-group">
            <label for="title">Title</label>
            <input id="title" name="title" type="text" class="form-control" value="${fn:escapeXml(param.title)}"/>
        </div>
        <input type="hidden" name="page" value="${param.page != null ? param.page : 1}"/>

        <div class="form-group">
            <label for="searchTitleType">Title Type</label>
            <select id="searchTitleType" name="searchTitleType" class="form-control">
                <option value="all" ${fn:escapeXml(param.searchTitleType) == 'all' ? 'selected' : ''}>All</option>
                <option value="movie" ${fn:escapeXml(param.searchTitleType) == 'movie' ? 'selected' : ''}>Movie</option>
                <option value="short" ${fn:escapeXml(param.searchTitleType) == 'short' ? 'selected' : ''}>Short</option>
                <option value="tvMovie" ${fn:escapeXml(param.searchTitleType) == 'tvMovie' ? 'selected' : ''}>TV Movie</option>
                <option value="tvSeries" ${fn:escapeXml(param.searchTitleType) == 'tvSeries' ? 'selected' : ''}>TV Series</option>
                <option value="tvEpisode" ${fn:escapeXml(param.searchTitleType) == 'tvEpisode' ? 'selected' : ''}>TV Episode</option>
                <option value="tvSpecial" ${fn:escapeXml(param.searchTitleType) == 'tvSpecial' ? 'selected' : ''}>TV Special</option>
                <option value="video" ${fn:escapeXml(param.searchTitleType) == 'video' ? 'selected' : ''}>Video</option>
                <option value="videoGame" ${fn:escapeXml(param.searchTitleType) == 'videoGame' ? 'selected' : ''}>Video Game</option>
            </select>
        </div>

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
                        <a class="page-link" href="findtitle?page=${currentPage - 1}&title=${fn:escapeXml(param.title)}&searchTitleType=${fn:escapeXml(param.searchTitleType)}">Previous</a>
                    </li>
                </c:if>
                <c:if test="${titles.size() == pageSize}">
                    <li class="page-item">
                        <a class="page-link" href="findtitle?page=${currentPage + 1}&title=${fn:escapeXml(param.title)}&searchTitleType=${fn:escapeXml(param.searchTitleType)}">Next</a>
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