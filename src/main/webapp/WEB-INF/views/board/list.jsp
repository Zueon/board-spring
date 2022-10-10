<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/WEB-INF/views/includes/header.jsp" %>
    <title>Title</title>
</head>
<body>
<%@include file="/WEB-INF/views/includes/nav.jsp" %>
<div class="container mb-3">
    <div class="row">
        <div class="col">
            <h2 class="mb-3">게시판</h2>
        </div>

        <div class="col">
            <form class="d-flex">
                <div class="col-sm-3">
                    <select class="form-select form-control" id="specificSizeSelect">
                        <option selected>--</option>
                        <option value="T">제목</option>
                        <option value="W">작성자</option>
                        <option value="C">내용</option>
                        <option value="TW">제목 + 작성자</option>
                        <option value="TC">제목 + 내용</option>
                        <option value="TWC">제목 + 작성자 + 내용</option>
                    </select>
                </div>
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>

    <table class="board-table table table-sm border-bottom">
        <thead class="thead-light">
        <tr>
            <th scope="col">번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">작성일</th>
            <th scope="col">수정일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="board" items="${list}">
            <tr>
                <td><c:out value="${board.bno}"/></td>
                <td><a href="/board/get?bno=${board.bno}&pageNum=${pageInfo.criteria.pageNum}">${board.title}</a></td>
                <td><c:out value="${board.writer}"/></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}"/></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--    aria-label :  ARIA는 HTML요소에 접근 가능한 설명용 텍스트를 넣을 수 있다 --%>
    <nav aria-label="pagination">
        <ul class="pagination justify-content-center">
            <c:if test="${pageInfo.prev}">
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:forEach var="page" begin="${pageInfo.firstPage}" end="${pageInfo.lastPage}">
                <li class="page-item ${pageInfo.criteria.pageNum == page? 'active' : ''}">
                    <a class="page-link" data-page="${page}">${page}</a>
                </li>
            </c:forEach>

            <c:if test="${pageInfo.next}">
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>

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

        // function movePage(e){
        //     e.preventDefault();
        //     const targetPage = $(this).data("page");
        //     self.location
        //
        // }

        $(".page-link").click(function (e){
            e.preventDefault();
            const targetPage = $(this).data("page");
            console.log(targetPage);
            self.location = "/board/list?pageNum=" + targetPage;
        })
    })


</script>
</body>
</html>
