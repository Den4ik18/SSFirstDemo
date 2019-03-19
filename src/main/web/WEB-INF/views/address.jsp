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
    <title>Title</title>
</head>
<body>
<section>
    <h3>Address info</h3>
    <table>
        <c:forEach items="${addresses}" var="address">
            <tr>
                <td><c:out value="${address.street}"/></td>
                <td><c:out value="${address.city}"/></td>
                <td><c:out value="${address.zipCode}"/></td>
                    <%-- <td>${address}</td>--%>
            </tr>
        </c:forEach>
    </table>
</section>
<li><a href="index.jsp">Go back</a></li>
</body>
</html>
