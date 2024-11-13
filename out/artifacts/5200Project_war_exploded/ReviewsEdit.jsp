<%--
  Created by IntelliJ IDEA.
  User: jiangtianhan
  Date: 11/12/24
  Time: 7:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Edit Review</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2 class="mt-5">Edit Your Review</h2>
    <form action="reviewsedit" method="post">
        <input type="hidden" name="reviewId" value="${review.reviewId}" />
        <div class="form-group">
            <label for="content">Content:</label>
            <textarea class="form-control" id="content" name="content" rows="4">${review.content}</textarea>
        </div>
        <div class="form-group">
            <label for="rating">Rating:</label>
            <input type="number" class="form-control" id="rating" name="rating" min="0" max="10" step="0.1" value="${review.rating}" />
        </div>
        <button type="submit" class="btn btn-primary">Update Review</button>
        <a href="reviewdelete?reviewId=${review.reviewId}" class="btn btn-danger">Delete Review</a>
    </form>
    <a href="index.jsp" class="btn btn-secondary mt-3">Back to Home</a>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
