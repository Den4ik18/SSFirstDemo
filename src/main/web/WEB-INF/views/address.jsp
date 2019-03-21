<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Denys
  Date: 19.03.2019
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Address List</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>
<body>
<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Employees </span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Street</th>
                <th>City</th>
                <th>Zip code</th>
                <th width="200"></th>
                <th width="200"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${addresses}" var="address">
                <tr>
                    <td>${address.street}</td>
                    <td>${address.city}</td>
                    <td>${address.zipCode}</td>

                    <td><a href="<c:url value='/edit-address-${address.id}' />" class="btn btn-success custom-width">Edit</a></td>
                    <td><a href="<c:url value='/delete'/>" class="btn btn-danger custom-width">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="well">
        <li><a href="<c:url value='/addAddress' />">Add New Address</a>
        <li><a href="index.jsp">Go back</a>
    </div>
</div>

</body>
</html>
