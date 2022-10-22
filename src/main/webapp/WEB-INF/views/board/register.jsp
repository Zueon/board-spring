<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <title>Title</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<%@include file="/WEB-INF/views/includes/nav.jsp" %>
<div class="container mb-3">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb p-1 pl-2 pr-2">
            <li class="breadcrumb-item">
                <a href="#">Home</a>
            </li>
            <li class="breadcrumb-item">
                <a href="/board/list">Board</a>
            </li>
            <li class="breadcrumb-item active" aria-current="page">
                Create Post
            </li>
        </ol>
    </nav>
    <form action="/board/register" method="post" id="registerForm">
        <div class="form-group">
            <label for="title">Title</label>
            <input
                    type="text"
                    id="title"
                    name="title"
                    class="form-control"
            />
        </div>

        <div class="form-group uploadFile">
            <label for="attachment">Attachment</label>
            <input type="file" name="attachment" class="form-control-file" id="attachment" multiple>
        </div>


        <div class="form-group">
            <label for="content">Content</label>
            <textarea
                    id="content"
                    name="content"
                    rows="5"
                    class="form-control"

            ></textarea>
        </div>
        <div class="form-group">
            <label for="title">Writer</label>
            <input
                    type="text"
                    id="writer"
                    name="writer"
                    class="form-control"
                    value='<sec:authentication property="principal.username"/>'
                    readonly
            />
        </div>
        <div>
            <a class="btn btn-primary" href="/board/list">
                Back
            </a>
            <button type="button" class="btn btn-primary submitBtn">
                Submit
            </button>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>


    <div class="card">
        <div class="card-header">Attachments</div>
        <div class="uploadResult">
            <ul id="file-list" class="list-group"></ul>
        </div>
    </div>
</div>

<script src="/resources/js/register.js"></script>
<script>

</script>

</body>
</html>
