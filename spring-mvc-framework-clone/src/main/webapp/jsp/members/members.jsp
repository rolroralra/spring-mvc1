<%--
  Created by IntelliJ IDEA.
  User: shinyoungkim
  Date: 2023/01/29
  Time: 10:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.springservlet.repository.MockMemberRepository" %>
<%@ page import="com.example.springservlet.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  MockMemberRepository memberRepository = MockMemberRepository.getInstance();
  List<Member> members = memberRepository.findAll();
%>
<html>
<head>
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
  for (Member member : members) {
    out.println(" <tr>");
    out.println("   <td>" + member.getId() + "</td>");
    out.println("   <td>" + member.getUsername() + "</td>");
    out.println("   <td>" + member.getAge() + "</td>");
    out.println(" </tr>");
  }
%>
  </tbody>
</table>
</body>
</html>
