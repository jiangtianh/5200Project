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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Title Details</title>
</head>
<body>
<div class="container">
  <h1 class="title">Title Details</h1>
  <c:if test="${not empty title}">
    <div class="detail">
      <span class="label">Title ID:</span> <c:out value="${title.titleId}"/>
    </div>
    <div class="detail">
      <span class="label">Title Type:</span> <c:out value="${title.titleType}"/>
    </div>
    <div class="detail">
      <span class="label">Primary Title:</span> <c:out value="${title.primaryTitle}"/>
    </div>
    <div class="detail">
      <span class="label">Original Title:</span> <c:out value="${title.originalTitle}"/>
    </div>
    <div class="detail">
      <span class="label">Is Adult:</span> <c:out value="${title.isAdult}"/>
    </div>
    <div class="detail">
      <span class="label">Start Year:</span> <c:out value="${title.startYear}"/>
    </div>
    <div class="detail">
      <span class="label">End Year:</span> <c:out value="${title.endYear}"/>
    </div>
    <div class="detail">
      <span class="label">Runtime Minutes:</span> <c:out value="${title.runtimeMinutes}"/>
    </div

    <%--Genres--%>
    <div class="detail">
      <span class="label">Genres:</span>
      <c:forEach var="genre" items="${genres}">
        <span><c:out value="${genre.genreName}"/></span><c:if test="${!genre.equals(genres[genres.size()-1])}">, </c:if>
      </c:forEach>
    </div>

    <%-- Ratings --%>
    <div class="detail">
      <span class="label">Ratings:</span>
      <c:if test="${not empty rating}">
        <c:out value="${rating.averageRating}"/> based on <c:out value="${rating.numVotes}"/> votes
      </c:if>
      <c:if test="${empty rating}">
        <p>No Ratings available yet</p>
      </c:if>
    </div>


  </c:if>
  <c:if test="${empty title}">
    <p>No details found for the given title ID.</p>
  </c:if>
</div>


<br/><br/><br/>
<a href="TitleFind.jsp">Search another title</a>

</body>
</html>