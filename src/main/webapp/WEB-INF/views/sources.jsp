<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: akprz
  Date: 12.01.2020
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>unknown-sources</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/framgents/header.jsp"/>

<h1 style="text-align: center">User skills</h1>
<table border="true">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Discription</th>
        <th>Skills</th>

    </tr>
    <c:forEach var="source" items="${sources}" varStatus="lp">
        <tr>
            <td>${lp.count}.</td>
            <td>${source.name}</td>
            <td>${source.description}</td>
            <td>
                <c:forEach var="skill" items="${source.attachedSkills}">
                    <p>${skill}</p>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>

</table>
<jsp:include page="/WEB-INF/views/framgents/footer.jsp"/>
</body>

</html>
