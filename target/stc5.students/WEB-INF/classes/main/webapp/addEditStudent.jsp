<%--
  Created by IntelliJ IDEA.
  User: Di
  Date: 20.04.2017
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddEditStudent</title>
</head>
<body>
<form method="post" action="/students/addEditStudent" >
    Name:  <input type="text" name="name"/>
    Age <input type="text" name="age"/>
    Group_id <input type="text" name="group_id"/>
    <input type="submit" value="add"/>
</form>

</body>
</html>
