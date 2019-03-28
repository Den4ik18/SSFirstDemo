<%@ page import="com.model.Employee" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add employee</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="<c:url value='/static/js/scriptForAddJob.js' />" rel="stylesheet"></script>
    <script src="<c:url value='/static/js/scriptForAddAddress.js' />" rel="stylesheet"></script>
</head>
<body>

<div class="generic-container">
    <div class="well lead">Modify employee</div>
    <form method="post" action="${pageContext.request.contextPath}/modifyEmployee" class="form-horizontal">
        <div id="inputTable" class="form-group">
            <% Employee employee = (Employee) request.getAttribute("employee"); %>
            <input type="hidden" name="id" id="id" value="${employee != null ? employee.getId() : 0}"/>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="name">First Name</label>
                    <div class="col-md-7">
                        <input type="text" path="name" id="name" name="name" required
                               value="${employee!=null ? employee.getName():""}" class="form-control input-sm"/>
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
                        <input type="text" path="lastName" id="lastName" name="lastName" required
                               value="${employee!=null ? employee.getLastName():""}" class="form-control input-sm"/>
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
                        <input type="text" path="phoneNumber" id="phoneNumber" name="phoneNumber" required
                               value="${employee!=null ? employee.getPhoneNumber():""}"
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
                        <input type="text" path="sex" id="sex" name="sex" required
                               value="${employee!=null ? employee.getSex():""}" class="form-control input-sm"/>
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
                        <input type="email" path="email" id="email" name="email" required
                               value="${employee!=null ? employee.getEmail():""}" class="form-control input-sm"/>
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
                        <input type="date" path="dateOfBirth" id="dateOfBirth" required
                               value="${employee!=null ? employee.getDateOfBirth():""}" name="dateOfBirth"
                               class="form-control input-sm"/>
                        <div class="has-error">
                            <errors path="dateOfBirth" class="help-inline"></errors>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-4">
                    <input class="btn btn-success custom-width3" type="button" onclick="addWork()"
                           value="Add job">
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-4">
                    <input class="btn btn-success custom-width3" type="button" onclick="addAddress()"
                           value="Add address">
                </div>
            </div>


        </div>
        <div class="row">
            <div class="form-actions floatLeft">
                <input type="submit" value="Submit" id="submit" name="submit" class="btn btn-success custom-width"/>
            </div>
            <div class="form-actions floatRight">
                <a href="${pageContext.request.contextPath}/employee" class="btn btn-danger custom-width">Go back</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>