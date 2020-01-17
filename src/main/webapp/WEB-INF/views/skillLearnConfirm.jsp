<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Skill confirm</title>
</head>
<body>
<p>Do you really know "${sourceName}" </p>
<form action="/sources/confirm" method="post">
    <div><input value="${sourceId}" type="hidden" id="sourceId" name="sourceId"></div>
    <input type="submit" value="Yes, I know">
</form>
</body>
</html>
