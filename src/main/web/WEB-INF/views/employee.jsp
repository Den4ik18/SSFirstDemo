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
    <title>Title</title>
</head>
<body>
<section>
    <h3>Employee info</h3>
    <table>
        <c:forEach items="${employees}" var="employee">
            <tr>
                <td><c:out value="${employee.name}"/></td>
                <td><c:out value="${employee.lastName}"/></td>
                <td><c:out value="${employee.phoneNumber}"/></td>
                <td><c:out value="${employee.sex}"/></td>
                <td><c:out value="${employee.email}"/></td>
                <td><c:out value="${employee.dateOfBirth}"/></td>
                <td><c:out value="${employee.jobs}"/></td>
                <td><c:out value="${employee.address.street}"/></td>
                <td><c:out value="${employee.address.city}"/></td>
                <td><c:out value="${employee.address.zipCode}"/></td>
               <%-- <td>${employee}</td>--%>
            </tr>
        </c:forEach>
    </table>
</section>
<li><a href="index.jsp">Go back</a></li>
</body>
</html>
