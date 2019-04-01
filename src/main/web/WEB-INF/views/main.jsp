<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Denys
  Date: 29.03.2019
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Resume</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/js/scriptForDelete.js' />" rel="stylesheet"/>
    <script type="text/javascript" src="static/js/scriptForDelete.js"></script>
</head>
<body>
<div class="resumeTitle">Резюме</div>
<div class="generic-container">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Employees</span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>Last name</th>
                <th>Phone number</th>
                <th>Sex</th>
                <th>Email</th>
                <th>Date of birth</th>
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
                    <td><a href="<c:url value='/update-employee?id=${employee.id}' />"
                           class="btn btn-success custom-width">Edit</a></td>
                    <td><a href="<c:url value='/fullinfo-employee?id=${employee.id}' />"
                           class="btn btn-success custom-width3">Get more info</a></td>
                    <td>
                        <button class="btn btn-danger custom-width" onClick=handleDelete(${employee.id},"employee")>
                            Delete
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="well">
        <a href="${pageContext.request.contextPath}/modifyEmployee" class="btn btn-success custom-width2">Add New
            Employee</a>
    </div>
</div>

<div class="details">
    <label class="txt"> Upload cv: </label><br>
    <form action="${pageContext.request.contextPath}/uploadFile" method="post" enctype="multipart/form-data">
        <input class="btn btn-success custom-width4" name="data" type="file" value="Chose file" accept=".json, .xml, .txt" >
        <input class="btn btn-success custom-width" type="submit" value="Upload"> <br>
    </form>
</div>
<div class="bt1">
    <a align="center" href="employee" class="button-main">Employee</a>
    <a align="center" href="job" class="button-main">Job</a>
    <a align="center" href="address" class="button-main">Address</a>
</div>
</body>
</html>

