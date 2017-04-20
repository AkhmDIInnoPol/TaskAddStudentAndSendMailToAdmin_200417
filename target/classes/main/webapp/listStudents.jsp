<%--
  Created by IntelliJ IDEA.
  User: Di
  Date: 19.04.2017
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% String message = (String) request.getAttribute("value");%>
<h1>

    <%=message%>



</h1>

<table>

    <c:forEach items="${requestScope.list}" var="student">
        <tr>
            <td><c:out value="${student.id}"></c:out></td>
            <td><c:out value="${student.name}"></c:out></td>
            <td><c:out value="${student.age}"></c:out></td>
            <td><c:out value="${student.groupId}"></c:out></td>
        </tr>
    </c:forEach>

</table>





</body>
</html>
