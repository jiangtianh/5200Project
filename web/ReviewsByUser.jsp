<%--
  Created by IntelliJ IDEA.
  User: jiangtianhan
  Date: 11/12/24
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Reviews</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Reviews by <c:out value="${username}"/></h1>
    <c:if test="${not empty reviews}">
        <div class="table-responsive">
            <table class="table table-bordered">
                <thead class="thead-light">
                <tr>
                    <th>Review ID</th>
                    <th>Title</th>
                    <th>Content</th>
                    <th>Rating</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="review" items="${reviews}">
                    <tr>
                        <td><c:out value="${review.reviewId}"/></td>
                        <td>
                            <a href="titledetail?titleId=${review.title.titleId}">
                                <c:out value="${review.title.primaryTitle}"/>
                            </a>
                        </td>
                        <td><c:out value="${review.content}"/></td>
                        <td><c:out value="${review.rating}"/></td>
                        <td>
                            <c:if test="${review.user.username == loggedInUser.username}">
                                <a href="reviewsedit?reviewId=${review.reviewId}" class="btn btn-warning">Edit</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
    <c:if test="${empty reviews}">
        <p class="text-center">No reviews found.</p>
    </c:if>
    <div class="text-center mt-4">
        <a href="index.jsp" class="btn btn-secondary">Back to Main</a>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>