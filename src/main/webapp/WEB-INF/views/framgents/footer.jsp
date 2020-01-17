<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<%--

  Created by IntelliJ IDEA.
  User: akprz
  Date: 11.01.2020
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Footer</title>
</head>
<body>
<p>Author: Arkadiusz Przytuła<p>
<p>Time: <%=LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd"))%></p>
</body>
</html>
