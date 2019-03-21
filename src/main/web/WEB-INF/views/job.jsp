<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Denys
  Date: 19.03.2019
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<section>
    <h3>Job info</h3>
    <table>
        <c:forEach items="${jobs}" var="job">
            <tr>
                <td><c:out value="${job.companyName}"/></td>
                <td><c:out value="${job.startDate}"/></td>
                <td><c:out value="${job.endDate}"/></td>
                <td><c:out value="${job.position}"/></td>
                     <%--<td>${job}</td>--%>
            </tr>
        </c:forEach>
    </table>
</section>
<li><a href="index.jsp">Go back</a></li>
</body>
</html>
