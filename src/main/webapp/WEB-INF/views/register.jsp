<%--
  Created by IntelliJ IDEA.
  User: akprz
  Date: 12.01.2020
  Time: 00:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/framgents/header.jsp"/>
<br/>
<form action="/register" method="post">
    <c:if test="${error!=null}">
        <p>User name is taken.</p>
    </c:if>
    <div><span><label for="userName">User name:  </label> </span><span><input value="${user.userName}" type="text"required placeholder="user name" id="userName" name="userName" ></span></div>
    <div><span><label for="password">Password:  </label> </span><span><input type="password"required value="${user.password}" id="password" name="password" ></span></div>
    <div><span><label for="firstName">First name:  </label> </span><span><input type="text"  value="${user.firstName}" placeholder="first name" id="firstName" name="firstName" ></span></div>
    <div><span><label for="lastName">last name:  </label> </span><span><input type="text" value="${user.lastName}" placeholder="last name" id="lastName" name="lastName" ></span></div>
<input type="submit" value="Register"><input type="reset" value="clear">
</form>
<br/>
<jsp:include page="/WEB-INF/views/framgents/footer.jsp"/>

</body>
</html>
