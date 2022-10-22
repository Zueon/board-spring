<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>sample/admin PAGE</h1>

<p>principal : <sec:authentication property="principal"/></p>
<p>MemberVO : <sec:authentication property="principal.member"/></p>
<p>MemberVO : <sec:authentication property="principal.member.userName"/></p>
<p>MemberVO : <sec:authentication property="principal.member.authList"/></p>

<a href="/customLogout">Logout</a>
</body>
</html>
