<%--
  Created by IntelliJ IDEA.
  User: jiangtianhan
  Date: 11/11/24
  Time: 9:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
    <title>Find a Person</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Find a Person</h1>
    <form action="personfind" method="post" class="mb-4">
        <div class="form-group">
            <label for="name">Name</label>
            <input id="name" name="name" type="text" class="form-control" value="${param.name}"/>
        </div>
        <input type="hidden" name="page" value="${param.page != null ? param.page : 1}"/>
        <button type="submit" class="btn btn-primary btn-block">Search</button>
        <div class="text-center mt-3">
            <span id="message" class="text-danger font-weight-bold">${message}</span>
        </div>
    </form>
    <div class="text-center mb-4">
        <a href="index.jsp" class="btn btn-secondary">Back to Main</a>
    </div>

    <c:if test="${not empty people}">
        <div class="table-responsive">
            <table class="table table-bordered">
                <thead class="thead-light">
                <tr>
                    <th>Person ID</th>
                    <th>Name</th>
                    <th>Birth Year</th>
                    <th>Death Year</th>
                    <th>Primary Profession</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="person" items="${people}">
                    <tr>
                        <td>
                            <a href="persondetail?personId=${person.personId}">
                                <c:out value="${person.personId}"/>
                            </a>
                        </td>
                        <td><c:out value="${person.name}"/></td>
                        <td><c:out value="${person.birthYear}"/></td>
                        <td><c:out value="${person.deathYear}"/></td>
                        <td><c:out value="${person.primaryProfession}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="personfind?page=${currentPage - 1}&name=${fn:escapeXml(param.name)}">Previous</a>
                    </li>
                </c:if>
                <c:if test="${people.size() == pageSize}">
                    <li class="page-item">
                        <a class="page-link" href="personfind?page=${currentPage + 1}&name=${fn:escapeXml(param.name)}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>
    <c:if test="${empty people}">
        <p class="text-center">No persons found with the given name.</p>
    </c:if>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
