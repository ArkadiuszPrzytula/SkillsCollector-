<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: akprz
  Date: 12.01.2020
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Skills</title>
</head>
<body>
<h1>User skills</h1>
<table border="true">
    <tr>
        <th>Id</th>
        <th>Skill</th>
        <th>Level</th>
    </tr>
    <jsp:include page="/WEB-INF/views/framgents/header.jsp"/>

    <% int count = 0; %>
    <c:forEach var="userSkills" items="${skills}">
        <tr>
            <td><%=++count%>.</td>
            <td>${userSkills.key}</td>
            <td>${userSkills.value}</td>
        </tr>
    </c:forEach>
</table>
    <jsp:include page="/WEB-INF/views/framgents/footer.jsp"/>
</body>
</html>
