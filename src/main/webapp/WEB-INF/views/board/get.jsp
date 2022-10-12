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
                <a href="/board/list?pageNum=${cri.pageNum}">Board</a>
            </li>
            <li class="breadcrumb-item active" aria-current="page">
                <c:out value="${board.title}"/>
            </li>
        </ol>
    </nav>
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
        <button class="btn btn-primary" data-oper="list">
            뒤로가기
        </button>
        <button class="btn btn-primary" data-oper="modify">
            수정하기
        </button>
    </div>
    <h4 class="mt-3">Comments</h4> <!-- 1 -->

<%--    <% if(comments.length){ %> <!-- 1-1 -->--%>
<%--    <div class="mt-3 border-bottom">--%>
<%--        <% comments.forEach(function(comment) { %> <!-- 1-2 -->--%>
<%--        <div class="border-top pt-1 pb-1">--%>
<%--            <div class="row">--%>
<%--                <div class="col-3 col-md-2 col-lg-1 pl-4"><%= comment.author.username %></div> <!-- 1-3 -->--%>
<%--                <div class="col-9 col-md-10 col-lg-11">--%>
<%--                    <div class="comment-show">--%>
<%--                        <div class="comment-text mb-3"><%= comment.text %></div> <!-- 1-4 -->--%>
<%--                        <small class="d-block">--%>
<%--                            (Created: <span data-date-time="<%= comment.createdAt %>"></span>) <!-- 1-5 -->--%>
<%--                        </small>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <% }) %>--%>
<%--    </div>--%>
<%--    <% } %>--%>
    <div class="mt-3"> <!-- 2 -->
        <form action="" method="post">
            <fieldset> <!-- 2-1 -->
                <div class="row">
                    <div class="col-2">
                        <input type="text" class="form-control" placeholder="작성자">
                    </div>
                    <div class="col-6">
                        <textarea name="text" rows="2" class="form-control"></textarea>
                    </div>
                    <div class="col-2">
                        <button type="submit" class="btn btn-primary h-100 mr-2">Add Comment</button>
                    </div>
                </div>

            </fieldset>
        </form>
    </div>


</div>
<script src="/resources/js/reply.js"></script>

<script>

    $(function () {

        $(".btn").click(function (e) {
            const oper = $(this).data("oper");
            if (oper === "list") self.location = "/board/list?pageNum=${cri.pageNum}&type=${cri.type}&keyword=${cri.keyword}"
            else if (oper === "modify") self.location = "/board/modify?bno=${board.bno}&pageNum=${cri.pageNum}&type=${cri.type}&keyword=${cri.keyword}"

        })

    })


    <%--replyService.add(--%>
    <%--    {--%>
    <%--        reply : "JS TEST",--%>
    <%--        replyer : "TEST WRITER1",--%>
    <%--        bno : ${board.bno}--%>
    <%--    },--%>
    <%--    (result) => {alert("RESULT : " + result)}--%>
    <%--)--%>

    <%--replyService.getList(--%>
    <%--    {--%>
    <%--        bno : ${board.bno},--%>
    <%--    }--%>
    <%--)--%>

    // replyService.remove(63)
    // replyService.modify({
    //     "rno": 61,
    //     "bno": 160,
    //     "reply": "JS TEST - Changed",
    // }, (result)=>{
    //     console.log(result)
    // })
    //
    replyService.get(61)


</script>
</body>
</html>
