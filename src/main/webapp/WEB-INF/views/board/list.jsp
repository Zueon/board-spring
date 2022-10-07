
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/WEB-INF/views/includes/header.jsp"%>
    <title>Title</title>
</head>
<body>
<%@include file="/WEB-INF/views/includes/nav.jsp"%>
<div class="container mb-3">
    <h2 class="mb-3">게시판</h2>
    <table class="board-table table table-sm border-bottom">
        <thead class="thead-light">
        <tr>
            <th scope="col">#번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">작성일</th>
            <th scope="col">수정일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="board" items="${list}" >
            <tr>
                <td ><c:out value="${board.bno}"/></td>
                <td><a href="/board/get?bno=${board.bno}">${board.title}</a></td>
                <td><c:out value="${board.writer}"/></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}"/></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="modal fade" tabindex="-1" id="myModal" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">처리 결과</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>게시물 처리가 완료되었습니다.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>
    <div>
        <a class="btn btn-primary" href="/board/register">
            작성하기
        </a>
    </div>
</div>
<script>
    $(function () {
        const result = '<c:out value="${result}" />'
        checkModal(result);

        function checkModal(result) {
            if (result === "") return;
            if (parseInt(result) > 0) $(".modal-body").html("게시글 " + parseInt(result) + "번이 등록되었습니다.");
            $("#myModal").modal("show");
        }
    })

    function onClick() {

    }
</script>
</body>
</html>
