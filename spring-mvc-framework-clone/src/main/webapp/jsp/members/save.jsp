<%--
  Created by IntelliJ IDEA.
  User: shinyoungkim
  Date: 2023/01/29
  Time: 10:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.springservlet.repository.MockMemberRepository" %>
<%@ page import="com.example.springservlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  MockMemberRepository memberRepository = MockMemberRepository.getInstance();
  System.out.println("/jsp/members/save.jsp");
  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));

  Member member = new Member(username, age);
  System.out.println("member = " + member);
  memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
  200 OK
  <ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
  </ul>

  <a href="/index.html">메인</a>
</body>
</html>
