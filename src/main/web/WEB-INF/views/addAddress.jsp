<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Denys
  Date: 21.03.2019
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add address</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>

<body>

<div class="generic-container">
    <div class="well lead">Add employee</div>
    <%--@elvariable id="employee" type="com.model.Employee"--%>
    <form method="post" class="form-horizontal">
        <input type="hidden" path="id" id="id"/>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="street">Street</label>
                <div class="col-md-7">
                    <input type="text" path="street" id="street" name="street" class="form-control input-sm"/>
                    <div class="has-error">
                        <errors path="street" class="help-inline"></errors>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="city">City</label>
                <div class="col-md-7">
                    <input type="text" path="city" id="city" name="city" class="form-control input-sm"/>
                    <div class="has-error">
                        <errors path="city" class="help-inline"></errors>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="zipCode">Zip code</label>
                <div class="col-md-7">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="text" path="zipCode" id="zipCode" name = "zipCode" class="form-control input-sm"
                                   disabled="true"/>
                        </c:when>
                        <c:otherwise>
                            <input type="text" path="zipCode" id="zipCode" name = "zipCode" class="form-control input-sm"/>
                            <div class="has-error">
                                <errors path="zipCode" class="help-inline"></errors>
                            </div>
                        </c:otherwise>
                    </c:choose>
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
                    href="<c:url value='address' />">Cancel</a>
                <%--  </c:otherwise>
              </c:choose>--%>
            </div>
        </div>
    </form>
</div>
</body>
</html>
