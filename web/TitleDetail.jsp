<%--
  Created by IntelliJ IDEA.
  User: jiangtianhan
  Date: 11/11/24
  Time: 11:38 AM
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
  <title>Title Details</title>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h1 class="text-center">Title Details</h1>
  <c:if test="${not empty title}">
    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title">Title ID: <c:out value="${title.titleId}"/></h5>
        <p class="card-text"><strong>Title Type:</strong> <c:out value="${title.titleType}"/></p>
        <p class="card-text"><strong>Primary Title:</strong> <c:out value="${title.primaryTitle}"/></p>
        <p class="card-text"><strong>Original Title:</strong> <c:out value="${title.originalTitle}"/></p>

          <%--Genres--%>
        <p class="card-text"><strong>Genres:</strong>
          <c:forEach var="genre" items="${genres}">
            <span><c:out value="${genre.genreName}"/></span><c:if test="${!genre.equals(genres[genres.size()-1])}">, </c:if>
          </c:forEach>
        </p>

          <%-- Ratings --%>
        <p class="card-text"><strong>Ratings:</strong>
          <c:if test="${not empty rating}">
            <c:out value="${rating.averageRating}"/> based on <c:out value="${rating.numVotes}"/> votes
          </c:if>
          <c:if test="${empty rating}">
            <span>No Ratings available yet</span>
          </c:if>
        </p>

          <%--Directors--%>
        <p class="card-text"><strong>Directors:</strong>
          <c:forEach var="director" items="${directors}">
            <span>
              <a href="persondetail?personId=${director.personId}">
                <c:out value="${director.name}"/>
              </a>
            </span><c:if test="${!director.equals(directors[directors.size()-1])}">, </c:if>
          </c:forEach>
        </p>

          <%--Writers--%>
        <p class="card-text"><strong>Writers:</strong>
          <c:forEach var="writer" items="${writers}">
            <span>
              <a href="persondetail?personId=${writer.personId}">
                <c:out value="${writer.name}"/>
              </a>
            </span><c:if test="${!writer.equals(writers[writers.size()-1])}">, </c:if>
          </c:forEach>
        </p>

          <%--Stars--%>
        <p class="card-text"><strong>Stars:</strong>
          <c:forEach var="star" items="${stars}">
            <span>
              <a href="persondetail?personId=${star.person.personId}"><c:out value="${star.person.name}"/></a> as <c:out value="${star.characters}"/>
            </span><c:if test="${!star.equals(stars[stars.size()-1])}">, </c:if>
          </c:forEach>
        </p>

        <p class="card-text"><strong>Crews:</strong>
          <c:forEach var="crew" items="${crews}">
            <span>
              <c:if test="${not empty crew.job}">
                <c:out value="${crew.job.replace('_', ' ')}"/>:
              </c:if>
              <a href="persondetail?personId=${crew.person.personId}"><c:out value="${crew.person.name}"/></a>
            </span><c:if test="${!crew.equals(crews[crews.size()-1])}">, </c:if>
          </c:forEach>
        </p>



        <p class="card-text"><strong>Is Adult:</strong> <c:out value="${title.isAdult}"/></p>
        <p class="card-text"><strong>Start Year:</strong> <c:out value="${title.startYear}"/></p>
        <p class="card-text"><strong>End Year:</strong> <c:out value="${title.endYear}"/></p>
        <p class="card-text"><strong>Runtime Minutes:</strong> <c:out value="${title.runtimeMinutes}"/></p>


      </div>
    </div>
    <a href="reviewcreate?titleId=${title.titleId}" class="btn btn-primary">Add Review</a>
    <a href="reviewsbytitle?titleId=${title.titleId}" class="btn btn-secondary">View Reviews</a>
  </c:if>

  <c:if test="${empty title}">
    <p class="text-center">No details found for the given title ID.</p>
  </c:if>

  <div class="text-center">
    <a href="TitleFind.jsp" class="btn btn-primary">Search another title</a>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>