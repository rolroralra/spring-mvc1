<%--
  Created by IntelliJ IDEA.
  User: shinyoungkim
  Date: 2023/01/29
  Time: 10:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.springservlet.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Member> members = (List<Member>) request.getAttribute("members");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<a href="/index.html">메인</a>

<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
<%
//    for (Member member : members) {
//        out.println(" <tr>");
//        out.println("   <td>" + member.getId() + "</td>");
//        out.println("   <td>" + member.getUsername() + "</td>");
//        out.println("   <td>" + member.getAge() + "</td>");
//        out.println(" </tr>");
//    }
%>
    <c:forEach var="item" items="${members}">
        <tr>
            <td>${item.id}</td>
            <td>${item.username}</td>
            <td>${item.age}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
