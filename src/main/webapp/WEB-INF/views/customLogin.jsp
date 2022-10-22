<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <title>Title</title>
</head>
<body>
<%@include file="/WEB-INF/views/includes/nav.jsp" %>

<h2>${error}</h2>
<h2>${logout}</h2>

<div class="container">

    <h3 class="mb-3">Login</h3>

    <form class="user-form" action="/login" method="post">

        <div class="form-group row">
            <label for="username" class="col-sm-3 col-form-label">Username</label>
            <div class="col-sm-9">
                <input type="text" id="username" name="username">
            </div>
        </div>

        <div class="form-group row">
            <label for="password" class="col-sm-3 col-form-label">Password</label>
            <div class="col-sm-9">
                <input type="password" id="password" name="password">

            </div>
        </div>

        <div class="form-group row">
            <div class="col-sm-9">
                <label>
                    <input name="remember-me" type="checkbox">Remember Me
                </label>
            </div>
        </div>


        <div class="mt-3">
            <input class="btn btn-primary submitBtn" type="button" value="Submit">
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

</div>

<script>
    $(".submitBtn").click(function (e){
        e.preventDefault();
        $("form").submit();
    })
</script>
</body>
</html>
