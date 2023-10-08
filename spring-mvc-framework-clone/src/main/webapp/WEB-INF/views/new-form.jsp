<%--
  Created by IntelliJ IDEA.
  User: shinyoungkim
  Date: 2023/01/29
  Time: 10:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<form action="save" method="post">
  <label for="username">username: </label>
  <input id="username" type="text" name="username"/>
  <label for="age">age: </label>
  <input id="age" type="text" name="age"/>
  <button type="submit">전송</button>
</form>

</body>
</html>
