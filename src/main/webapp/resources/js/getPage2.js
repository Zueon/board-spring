/** form input 데이터들을 json 형태로 변환*/
jQuery.fn.serializeObject = function () {
    var obj = null;
    try {
        if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
            var arr = this.serializeArray();
            if (arr) {
                obj = {};
                jQuery.each(arr, function () {
                    obj[this.name] = this.value;
                });
            }//if ( arr ) {
        }
    } catch (e) {
        alert(e.message);
    } finally {
    }

    return obj;
};



function onButtonClick(e){
    const oper = $(this).data("oper");
    const pageNum =$("#pageNum").val();
    const type =$("#type").val();
    const keyword =$("#keyword").val();
    const bno =$("#bnoVal").val();
    const currPage = $(".active").children(".page-link").data("page");

    console.log(oper);

    let nextPage = `/board/${oper}?pageNum=${pageNum}&type=${type}&keyword=${keyword}`

    // 뒤로 가기
    if (oper === "list") {
        self.location = nextPage;
    }

    // 게시물 수정하기
    else if (oper === "modify") {
        self.location = nextPage + `&bno=${bno}`
    }

    // 댓글 추가
    else if (oper === "add-comment") {
        e.preventDefault();

        const commentForm = $("#commentForm").serializeObject();

        replyService.add(commentForm, function () {
            $("#reply").val("");
            $("#replyer").val("");
            fetchComments(bno, -1);
        })
    }

    // 댓글 수정 버튼 클릭 - 모달 창 열림
    else if (oper === "open-modal") {
        const replyer = $(this).parents(".comment").find(".comment-replyer").text();
        const reply = $(this).parents(".comment").find(".comment-reply").text();
        const rno = $(this).parents(".comment").attr("id");

        $("#rno").val(rno);
        $("#reply-modal").val(reply);
        $("#replyer-modal").val(replyer);

        $("#exampleModal").modal("show");
    }

    // 댓글 삭제하기
    else if (oper === "delete-comment") {
        const rno = $(this).parents(".comment").attr("id");

        const delChk = confirm("삭제하시겠습니까?");
        if (delChk){
            replyService.remove(rno, function (res) {
                fetchComments(bno,currPage)
            })
        }
    }

    // 모달창에서 댓글 수정 클릭
    else if (oper === "modify-comment"){
        const formData = $("#editCommentForm").serializeObject();

        replyService.modify(formData, function (res) {
            fetchComments(bno,currPage)
        })
    }

}


// 게시물의 댓글 불러오기
function fetchComments(bno, page) {
    console.log(page)
    replyService.getList(
        {
            bno,
            page: page || 1
        },
        // AJAX의 결과로 댓글의 개수와 댓글 리스트를 반환받는다
        (replyCnt, comments) => {
            // 댓글을 추가한 경우, 댓글 목록의 마지막 페이지로 이동한다.
            if(page == -1) {
                page = Math.ceil(replyCnt / 10.0);
                fetchComments(bno, page)
                return;
            }

            // 댓글이 없을 경우
            if(comments == null || comments.length == 0) {
                $("#comments").empty();
                return;
            }

            let el = "";

            // 댓글 리스트를 한 요소씩 순회하면서 HTML element 를 추가한다.
            $.each(comments, (idx, comment) => {
                 el +=
                    `<div class="row mt-3 comment" id=${comment.rno}>
                        <div class="col-1 comment-replyer" >${comment.replyer} </div>
                        <div class="col-9 comment-reply" style="word-break: break-all"> ${comment.reply}</div>
                        <small class="d-block col-1" >
                            <span> ${replyService.displayTime(comment.replyDate)} </span>
                        </small>
                        <div class="col-1">
                        <i class="bi bi-x-square mr-1 actionBtn" data-oper = "delete-comment"  > </i>
                        <i class="bi bi-pencil-square actionBtn" data-oper = "open-modal"></i>
                        </div>
                         
                      </div>`

            })
            $("#comments").html(el);
            showReplyPagenation(replyCnt, page);

        }
    )
}

// 페이지 목록 출력
function showReplyPagenation(replyCnt, currentPage){


    let lastPage = Math.ceil(currentPage / 10.0) * 10;
    let firstPage = lastPage - 9;

    // 1~10 페이지의 경우 prev 버튼이 존재하지 않는다. 11~20페이지의 경우 prev 버튼이 존재한다
    let prev = firstPage != 1;
    let next = false;

    if (lastPage  * 10 >= replyCnt) lastPage = Math.ceil(replyCnt/ 10.0);
    if (lastPage  * 10 < replyCnt) next = true;

    let page = "";
    let prevBtn = "";
    let nextBtn = "";
    let active = "";

    for (let i = firstPage; i <= lastPage ; i++) {
        active = currentPage == i ? "active" : "";
        page += `<li class="page-item ${active}"><a class="page-link" href="#" data-page="${i}">${i}</a></li>`
    }

    if (prev) prevBtn =
        `<li class="page-item">
            <a class="page-link" href="#" aria-label="Previous" data-page="${firstPage -1}">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>`

    if (next) nextBtn =
        `<li class="page-item">
            <a class="page-link" href="#" aria-label="Next" data-page="${lastPage+1}">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>`

    let el =
        `<nav aria-label="Page navigation example">
            <ul class="pagination  justify-content-end">
                ${prevBtn}
                ${page}
                ${nextBtn}
            </ul>
        </nav>`
    $("#replyPage").html(el);

}

function pageClick(){
    const targetPage = $(this).data("page");
    const bno = $("#bno").val();

    fetchComments(bno, targetPage)

}

$(function(){
    const bno =$("#bnoVal").val();

    $.ajax({
        url:"/board/getAttachments",
        data: {bno},
        success : function(data){
            console.log(data);
            let liEl = "";

            $.each(data, function(idx, file){
                if(file.fileType){
                    liEl += `
                <li class="list-group-item" data-filename="${file.filename}" data-uuid="${file.uuid}" data-uploadPath="${file.uploadPath}" data-type="${file.fileType}">
                  <img src="/display?filename=${file.uploadPath}/s_${file.uuid}_${file.filename}"/>
                </li>
                `
                } else {
                    liEl += `
                  <li class="list-group-item" data-filename="${file.filename}" data-uuid="${file.uuid}" data-uploadPath="${file.uploadPath}" data-type="${file.fileType}">
                    <img src='/resources/images/file-icon.png'/>${file.filename}
                  </li>`
                }
            })
            $("#file-list").html(liEl);
        }
    })

    $(document).on("click", ".list-group-item", function(e){
        const liEl = $(this);
        const path = `${liEl.data("uploadpath")}/${liEl.data("uuid")}_${liEl.data("filename")}`

        if(liEl.data("type")){
            window.open(`/display?filename=${path}`)
        } else {
            self.location = `/download?filename=${path}`
        }

    })

    function showImage(fileCallPath){
        alert(fileCallPath);

    }

})