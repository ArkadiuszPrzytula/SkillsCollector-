<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: akprz
  Date: 12.01.2020
  Time: 09:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/framgents/header.jsp"/>
<br/>
<form action="/login" method="post">
    <c:if test="${error!=null}">
        <p>${error}</p>
    </c:if>
    <div><span><label for="userName">User name:  </label> </span><span><input value="${user.userName}" type="text"required placeholder="user name" id="userName" name="userName" ></span></div>
    <div><span><label for="password">Password:  </label> </span><span><input type="password"required value="${user.password}" id="password" name="password" ></span></div>
    <input type="submit" value="login">
</form>
</body>
</html>
