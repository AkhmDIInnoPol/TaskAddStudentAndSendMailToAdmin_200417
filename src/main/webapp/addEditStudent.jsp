<%--
  Created by IntelliJ IDEA.
  User: Di
  Date: 20.04.2017
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>AddEditStudent</title>
</head>
<body>
<form method="post" action="/students/addEditStudent" >
    Name:  <input type="text" name="name"/>
    Age <input type="text" name="age"/>
    Group_id <input type="text" name="group_id"/>
    <input type="submit" name = "buttonClicked" value="addStudentClicked"/>
</form>


<b1>
    Редактирование студента.
</b1>

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

<b1>

    <form method="post" action="/students/addEditStudent" >
        Id:         <input type="text" name="studentId"/>
        Name:       <input type="text" name="name"/>
        Age         <input type="text" name="age"/>
        Group_id    <input type="text" name="group_id"/>
        <input type="submit" name = "buttonClicked" value="editStudentClicked"/>
    </form>

</b1>



</body>
</html>
