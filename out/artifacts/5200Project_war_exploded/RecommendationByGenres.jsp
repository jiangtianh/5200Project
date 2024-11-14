<%--
  Created by IntelliJ IDEA.
  User: jiangtianhan
  Date: 11/13/24
  Time: 6:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Recommended Titles by Genres</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Recommended Titles by Genres</h1>
    <table class="table table-bordered table-striped">
        <thead>
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
        <c:forEach var="title" items="${recommendedTitles}">
            <tr>
                <td>
                    <a href="titledetail?titleId=${title.titleId}">
                        <c:out value="${title.titleId}"/>
                    </a>
                </td>
                <td>${title.titleType}</td>
                <td>${title.primaryTitle}</td>
                <td>${title.originalTitle}</td>
                <td>${title.isAdult}</td>
                <td>${title.startYear}</td>
                <td>${title.endYear}</td>
                <td>${title.runtimeMinutes}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="text-center mt-4">
        <c:if test="${pageNumber > 1}">
            <a href="recommendationbygenres?pageNumber=${pageNumber - 1}" class="btn btn-primary">Previous</a>
        </c:if>
        <a href="recommendationbygenres?pageNumber=${pageNumber + 1}" class="btn btn-primary">Next</a>
    </div>
    <div class="text-center mt-4">
        <a href="index.jsp" class="btn btn-secondary">Back to Home</a>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>