<%--
  Created by IntelliJ IDEA.
  User: akprz
  Date: 11.01.2020
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Navigation</title>
    <style>
        li {
            display: inline;
            padding-right: 10px;
            padding-left: 10px;
        }
        a{
            text-decoration: none;
            color: black;
        }
        a:hover
        {
            color: red;
        }
    </style>
</head>
<body>
<ul style="list-style-type: none" }>
    <li><a href="/user/skills">Skills</a></li>
    <li><a href="/register">Register</a></li>
    <li><a href="/login">Login</a></li>
    <li><a href="/logout">Logout</a></li>
    <li><a href="/users/sources">Sources</a></li>
    <li><a href="/users/unknown-sources">Unknown sources</a></li>
</ul>
</body>
</html>
