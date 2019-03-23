<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Denys
  Date: 22.03.2019
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add job</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>

<body>

<div class="generic-container">
    <div class="well lead">Add job</div>
    <form method="post" action="${pageContext.request.contextPath}/job" class="form-horizontal">
        <input type="hidden" path="id" id="id"/>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="companyName">Company Name</label>
                <div class="col-md-7">
                    <input type="text" path="companyName" id="companyName" name="companyName"
                           class="form-control input-sm"/>
                    <div class="has-error">
                        <errors path="companyName" class="help-inline"></errors>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="startDate">Start date</label>
                <div class="col-md-7">
                    <input type="date" path="startDate" id="startDate" name="startDate" class="form-control input-sm"/>
                    <div class="has-error">
                        <errors path="startDate" class="help-inline"></errors>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="endDate">End Date</label>
                <div class="col-md-7">
                    <input type="date" path="endDate" id="endDate" name="endDate" class="form-control input-sm"/>
                    <div class="has-error">
                        <errors path="endDate" class="help-inline"></errors>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="position">Position</label>
                <div class="col-md-7">
                    <input type="text" path="position" id="position" name="position" class="form-control input-sm"/>
                    <div class="has-error">
                        <errors path="position" class="help-inline"></errors>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatRight">
                <%--<c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a
                            href="<c:url value='employee' />">Cancel</a>
                    </c:when>
                    <c:otherwise>--%>
                <input type="submit" value="Add" class="btn btn-primary btn-sm"/> or <a
                    href="<c:url value='job' />">Cancel</a>
                <%--  </c:otherwise>
              </c:choose>--%>
            </div>
        </div>
    </form>
</div>
</body>
</html>
