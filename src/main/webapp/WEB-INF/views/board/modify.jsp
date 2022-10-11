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
    <form name="frm" action="/board/modify?pageNum=${cri.pageNum}&type=${cri.type}&keyword=${cri.keyword}" method="post">
        <div class="form-group">
            <label for="title">BNO</label>
            <input
                    type="text"
                    id="bno"
                    name="bno"
                    class="form-control"
                    value='<c:out value="${board.bno}"/>'
                    readonly
            />
        </div>
        <div class="form-group">
            <label for="title">Title</label>
            <input
                    type="text"
                    id="title"
                    name="title"
                    class="form-control"
                    value='<c:out value="${board.title}"/>'
            />
        </div>

        <div class="form-group">
            <label for="content">Content</label>
            <textarea
                    id="content"
                    name="content"
                    rows="5"
                    class="form-control"
            >${board.content}</textarea>
        </div>
        <div class="form-group">
            <label for="title">Writer</label>
            <input
                    type="text"
                    id="writer"
                    name="writer"
                    class="form-control"
                    value=${board.writer}
                            readonly
            />
        </div>
        <div>
            <button class="btn btn-secondary" data-oper="list">
                목록보기
            </button>

            <button class="btn btn-primary" data-oper="modify">
                수정하기
            </button>
            <button class="btn btn-danger" data-oper="remove">
                삭제하기
            </button>
        </div>

        <input type="hidden" name="pageNum" value="${cri.pageNum}">
    </form>
</div>
<script>
    $(function () {
        const frm = $("form");

        $(".btn").click(function (e) {
            e.preventDefault();
            const oper = $(this).data("oper");
            if (oper === "list") {
                self.location = "/board/list?pageNum=${cri.pageNum}&type=${cri.type}&keyword=${cri.keyword}";
                return;
            } else if (oper === "remove") frm.attr("action", "/board/remove?pageNum=${cri.pageNum}&type=${cri.type}&keyword=${cri.keyword}");

            frm.submit();
        })

    })
</script>
</body>
</html>
