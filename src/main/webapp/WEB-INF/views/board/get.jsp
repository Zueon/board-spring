<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <title>Title</title>
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
                <c:out value="${board.title}"/>
            </li>
        </ol>
    </nav>

        <div class="form-group">
            <label for="title">Title</label>
            <input
                    type="text"
                    id="title"
                    name="title"
                    class="form-control"
                    value='<c:out value="${board.title}"/>'
                    readonly
            />
        </div>

        <div class="form-group">
            <label for="content">Content</label>
            <textarea
                    id="content"
                    name="content"
                    rows="5"
                    class="form-control"
                    readonly
            ><c:out value="${board.content}"/></textarea>
        </div>
        <div class="form-group">
            <label for="title">Writer</label>
            <input
                    type="text"
                    id="writer"
                    name="writer"
                    class="form-control"
                    value='<c:out value="${board.writer}"/>'
                    readonly
            />
        </div>
        <div>
            <button class="btn btn-primary" data-oper = "list">
                뒤로가기
            </button>
            <button class="btn btn-primary" data-oper = "modify" >
                수정하기
            </button>
        </div>

</div>
<script>
    $(function () {
        $(".btn").click(function(e){

            const oper = $(this).data("oper");
            if (oper === "list") self.location = "/board/list"
            else if (oper=== "modify") self.location = `/board/modify?bno=${board.bno}`

        })
    })
</script>
</body>
</html>
