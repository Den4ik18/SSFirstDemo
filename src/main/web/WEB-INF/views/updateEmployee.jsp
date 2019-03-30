<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Denys
  Date: 25.03.2019
  Time: 12:16
  To change this template use File | Settings | File Templates.
---%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="form" uri=""%>--%>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update employee</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>

<body>
<div class="generic-container">
    <jsp:useBean id="employee" scope="request" type="com.model.Employee"/>
    <div class="well lead">Update employee</div>
    <form method="post" action="${pageContext.request.contextPath}/update-employee" class="form-horizontal">
            <input type="hidden" path="id" id="id"/>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="name">First Name</label>
                    <div class="col-md-7">
                        <input type="text" path="name" id="name" name="name" value="${employee.name}" placeholder="${employee.name}" class="form-control input-sm"/>
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
                        <input type="text" path="lastName" id="lastName" name="lastName" value="${employee.lastName}" placeholder="${employee.lastName}" class="form-control input-sm"/>
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
                        <input type="text" path="phoneNumber" id="phoneNumber" name = "phoneNumber" value="${employee.phoneNumber}" placeholder="${employee.phoneNumber}" class="form-control input-sm"/>
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
                        <input type="text" path="sex" id="sex" name = "sex" value="${employee.sex}" placeholder="${employee.sex}" class="form-control input-sm"/>
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
                        <input type="email" path="email" id="email" name="email" value="${employee.email}" placeholder="${employee.email}" class="form-control input-sm"/>
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
                        <input type="date" path="dateOfBirth" id="dateOfBirth" name="dateOfBirth" value="${employee.dateOfBirth}" placeholder="${employee.dateOfBirth}" class="form-control input-sm"/>
                        <div class="has-error">
                            <errors path="dateOfBirth" class="help-inline"></errors>
                        </div>
                    </div>
                </div>
           </div>

            <div class="row">
                <div class="form-actions floatRight">
                    <input type="submit" value="Save" class="btn btn-primary btn-sm"/> or <a
                            href="<c:url value='employee' />">Cancel</a>
                </div>
            </div>
        </form>
</div>
</body>
</html>
