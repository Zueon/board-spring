<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal" var="pinfo"/>

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
                <c:out value="${board.title}"/>
            </li>
        </ol>
    </nav>
    <form name="frm" action="/board/modify?pageNum=${cri.pageNum}&type=${cri.type}&keyword=${cri.keyword}" method="post"
          id="modifyForm">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

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

        <div class="card mt-5">
            <div class="card-header">Attachments</div>
            <input type="file" name="attachment" class="form-control-file" id="attachment" multiple>
            <div class="attachments">
                <ul id="file-list" class="list-group"></ul>
            </div>
        </div>

        <div class="mt-5">
            <button class="btn btn-secondary" data-oper="list">
                목록보기
            </button>
            <sec:authorize access="isAuthenticated()">
                <c:if test="${pinfo.username eq board.writer}">
                    <button class="btn btn-primary" data-oper="modify">
                        수정하기
                    </button>
                    <button class="btn btn-danger" data-oper="remove">
                        삭제하기
                    </button>
                </c:if>
            </sec:authorize>
        </div>


    </form>
    <input type="hidden" name="pageNum" id="pageNum" value="${cri.pageNum}">
    <input type="hidden" name="type" id="type" value="${cri.type}">
    <input type="hidden" name="keyword" id="keyword" value="${cri.keyword}">
</div>
<script src="/resources/js/modify.js"></script>
<script>

</script>
</body>
</html>
