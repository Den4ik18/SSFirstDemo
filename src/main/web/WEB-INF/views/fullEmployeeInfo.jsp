<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Denys
  Date: 26.03.2019
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>All info about employee</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>

<body>

<div class="generic-container">
    <jsp:useBean id="employee" scope="request" type="com.model.Employee"/>
    <div class="well lead">All info about employee</div>
    <form method="post" action="${pageContext.request.contextPath}/fullinfo-employee?action=submit" class="form-horizontal">
        <input type="hidden" path="id" id="id"/>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="name">First Name</label>
                <div class="col-md-7">
                    <input type="text" path="name"  id="name" disabled name="name" value="${employee.name}"
                           placeholder="${employee.name}" class="form-control input-sm"/>
                    <div class="has-error">
                        <errors path="name" class="help-inline"></errors>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="lastName">Last Name</label>
                <div class="col-md-7">
                    <input type="text" path="lastName"  id="lastName" disabled name="lastName"
                           value="${employee.lastName}" placeholder="${employee.lastName}"
                           class="form-control input-sm"/>
                    <div class="has-error">
                        <errors path="lastName" class="help-inline"></errors>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="phoneNumber">Phone number</label>
                <div class="col-md-7">
                    <input type="text" path="phoneNumber" id="phoneNumber" disabled name="phoneNumber"
                           value="${employee.phoneNumber}" placeholder="${employee.phoneNumber}"
                           class="form-control input-sm"/>
                    <div class="has-error">
                        <errors path="phoneNumber" class="help-inline"></errors>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="sex">Sex</label>
                <div class="col-md-7">
                    <input type="text" path="sex"  id="sex" disabled name="sex" value="${employee.sex}"
                           placeholder="${employee.sex}" class="form-control input-sm"/>
                    <div class="has-error">
                        <errors path="sex" class="help-inline"></errors>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="email">Email</label>
                <div class="col-md-7">
                    <input type="email" path="email"  id="email" disabled name="email" value="${employee.email}"
                           placeholder="${employee.email}" class="form-control input-sm"/>
                    <div class="has-error">
                        <errors path="email" class="help-inline"></errors>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="dateOfBirth">Date of Birth</label>
                <div class="col-md-7">
                    <input type="date" path="dateOfBirth"  id="dateOfBirth" disabled name="dateOfBirth"
                           value="${employee.dateOfBirth}" placeholder="${employee.dateOfBirth}"
                           class="form-control input-sm"/>
                    <div class="has-error">
                        <errors path="dateOfBirth" class="help-inline"></errors>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="address">Address</label>
                <div class="col-md-7">
                    <input type="text" path="address"  id="address" disabled name="address" required value="${employee.address}"
                            class="form-control input-sm"/>
                    <div class="has-error">
                        <errors path="address" class="help-inline"></errors>
                    </div>
                </div>
            </div>
        </div>
        <div class=\"form-group\">Working places</div>
        <c:forEach items="${jobs}" var="job">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="companyName">Company name</label>
                    <div class="col-md-7">
                        <input type="text" path="companyName"  id="companyName" disabled name="companyName"
                               value="${job.getCompanyName()}" placeholder="${job.getCompanyName()}"
                               class="form-control input-sm"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="startDate">Start date</label>
                    <div class="col-md-7">
                        <input type="text" path="startDate"  id="startDate" disabled name="startDate"
                               value="${job.getStartDate()}" placeholder="${job.getStartDate()}"
                               class="form-control input-sm"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="endDate">End date</label>
                    <div class="col-md-7">
                        <input type="text" path="endDate"  id="endDate" disabled name="endDate"
                               value="${job.getEndDate()}" placeholder="${job.getEndDate()}"
                               class="form-control input-sm"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="position">Position</label>
                    <div class="col-md-7">
                        <input type="text" path="position"  id="position" disabled name="position"
                               value="${job.getPosition()}" placeholder="${job.getPosition()}"
                               class="form-control input-sm"/>
                    </div>
                </div>
            </div>

        </c:forEach>

        <div class="row">
            <div class="form-actions floatLeft">
                <input type="submit" value="Convert tp pdf" class="btn btn-success custom-width"/> or
                <a href="<c:url value='/convertToYaml?id=${employee.id}' />"
                   class="btn btn-success custom-width">Convert to Yaml</a>
            </div>
            <div class="form-actions floatRight">
                <a href="<c:url value='employee' />" class="btn btn-success custom-width">Go Back</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>
