<%--
  Created by IntelliJ IDEA.
  User: jueon
  Date: 10/21/22
  Time: 2:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Logout Page</h1>
<form action="/customLogout" method="post">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  <button>로그아웃</button>
</form>
</body>
</html>
