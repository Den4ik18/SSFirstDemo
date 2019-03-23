<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Denys
  Date: 19.03.2019
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Users List</title>
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
                <th>Name</th>
                <th>Last name</th>
                <th>Phone number</th>
                <th>Sex</th>
                <th>Email</th>
                <th>Date of birth</th>
                <th width="200"></th>
                <th width="200"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <td>${employee.name}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.phoneNumber}</td>
                    <td>${employee.sex}</td>
                    <td>${employee.email}</td>
                    <td>${employee.dateOfBirth}</td>
                    <td><a href="<c:url value='/edit-employee-${employee.id}' />" class="btn btn-success custom-width">Edit</a></td>
                    <td><a href="<c:url value='/delete-employee?id=${employee.id}' />" class="btn btn-danger custom-width">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="well">
        <a href="<c:url value='/addEmployee' />" class="btn btn-success custom-width2">Add New User</a>
    </div>
    <li><a href="index.jsp">Go back</a>
</div>

</body>
</html>
