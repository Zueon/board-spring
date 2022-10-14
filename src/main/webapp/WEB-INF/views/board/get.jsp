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
    <h4 class="mt-3">Comments</h4>


    <div class="mt-3">
        <form action="/replies/new" method="post" id="commentForm">
            <input type="hidden" name="bno" value="${board.bno}">
            <fieldset>
                <div class="row">
                    <div class="col-2">
                        <input type="text" class="form-control" placeholder="작성자" name="replyer" id = "replyer">
                    </div>
                    <div class="col-8">
                        <textarea name="reply" rows="2" class="form-control" id = "reply"></textarea>
                    </div>
                    <div class="col-2">
                        <button type="submit" class="cbtn btn btn-primary h-100 mr-2" data-oper="add">Add Comment</button>
                    </div>
                </div>

            </fieldset>
        </form>
    </div>

        <div class="mt-3 border-bottom">
            <div class="border-top pt-1 pb-1" id="comments">

            </div>
        </div>

    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">댓글 수정하기 </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="editCommentForm">
                        <div class="form-group">
                            <input type="hidden" id="rno" name="rno">
                            <label for="replyer-modal" class="col-form-label">작성자:</label>
                            <input type="text" class="form-control" id="replyer-modal" name="replyer"  readonly>
                        </div>
                        <div class="form-group">
                            <label for="reply-modal" class="col-form-label">댓글:</label>
                            <textarea class="form-control" id="reply-modal" name="reply" ></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary commentBtn" id="submitModal" data-oper="submit-modify">Send message</button>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="/resources/js/reply.js"></script>
<script src="/resources/js/getPage.js"></script>
<script>



    $(document).on("click", ".commentBtn",commentClick);


    $(function () {
        const bno = ${board.bno};

        const cri = {
            pageNum : "${cri.pageNum}",
            type : "${cri.type}",
            keyword : "${cri.keyword}"
        }

        fetchComments(bno);
        onBtnClick(cri, bno)

    })



</script>
</body>
</html>
