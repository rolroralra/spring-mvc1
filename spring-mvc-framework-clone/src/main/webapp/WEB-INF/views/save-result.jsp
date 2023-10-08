<%--
  Created by IntelliJ IDEA.
  User: shinyoungkim
  Date: 2023/01/29
  Time: 10:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.springservlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%
    Member member = (Member) request.getAttribute("member");
%>--%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<%--<ul>
    <li>id=<%= member.getId()%></li>
    <li>username=<%= member.getUsername()%></li>
    <li>age=<%= member.getAge()%></li>
</ul>--%>

<ul>
    <li>id=${member.id}</li>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
</ul>

<a href="/index.html">메인</a>

</body>
</html>
