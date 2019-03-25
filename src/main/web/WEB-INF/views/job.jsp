<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Denys
  Date: 19.03.2019
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Job List</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/js/scriptForDelete.js' />" rel="stylesheet"/>
    <script type="text/javascript" src="static/js/scriptForDelete.js"></script>
</head>
<body>
<div class="generic-container">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">List of Jobs</span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Company name</th>
                <th>Start date</th>
                <th>End date</th>
                <th>Position</th>
                <th width="200"></th>
                <th width="200"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${jobs}" var="job">
                <tr>
                    <td>${job.companyName}</td>
                    <td>${job.startDate}</td>
                    <td>${job.endDate}</td>
                    <td>${job.position}</td>
                    <td><a href="<c:url value='/update-job?id=${job.id}' />" class="btn btn-success custom-width">Edit</a></td>
                    <td><button class="btn btn-danger custom-width" onClick = handleDelete(${job.id},"job")>Delete</button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="well">
        <a href="${pageContext.request.contextPath}/job?add=1" class="btn btn-success custom-width2">Add New Job</a>
    </div>
    <a href="${pageContext.request.contextPath}/">Go back</a>
</div>

</body>
</html>
