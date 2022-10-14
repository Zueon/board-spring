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

function init(cri, bno){
    onBoardBtnClick(cri, bno);
    onCommentBtnClick();
    fetchComments2(bno,1);
}


function onBoardBtnClick(cri, bno){
    $(".btn").click(function (e) {
        const oper = $(this).data("oper");
        let nextPage = `/board/${oper}?pageNum=${cri.pageNum}&type=${cri.type}&keyword=${cri.keyword}`

        if (oper === "list") {
            self.location = nextPage;
        } else if (oper === "modify") {
            self.location = nextPage + `&bno=${bno}`
        }
    })
}

function onCommentBtnClick(){
    $(".cbtn").click(function (e) {
        e.preventDefault();
        const formData = $("#commentForm").serializeObject();
        replyService.add(formData, function () {
            $("#commentForm")[0].reset();
            $("#comments").empty();
            $("#replyPage").empty();
            fetchComments2(formData.bno, -1);
        })
    })

}




function fetchComments(bno) {
    replyService.getList(
        {
            bno,
            page: 1
        },
        (comments) => {
            $.each(comments, (idx, comment) => {
                let el =
                    `<div class="row mt-3 comment" id=${comment.rno}>
                        <div class="col-1 comment-replyer" >${comment.replyer} </div>
                        <div class="col-9 comment-reply" style="word-break: break-all"> ${comment.reply}</div>
                        <small class="d-block col-1" >
                            <span> ${replyService.displayTime(comment.replyDate)} </span>
                        </small>
                        <div class="col-1">
                        <i class="bi bi-x-square mr-1 commentBtn" data-oper = "deleteComment"  > </i>
                        <i class="bi bi-pencil-square commentBtn" data-oper = "modifyComment"></i>
                        </div>
                         
                      </div>`
                $("#comments").append(el);
            })
        }
    )
}

function fetchComments2(bno, page) {
    console.log(page)
    replyService.getList(
        {
            bno,
            page: page || 1
        },
        (replyCnt, comments) => {
            console.log(replyCnt);
            console.log(comments)

            if(page == -1) {
                page = Math.ceil(replyCnt / 10.0);
                fetchComments2(bno, page)
                return;
            }

            if(comments == null || comments.length == 0) return;

            $.each(comments, (idx, comment) => {
                let el =
                    `<div class="row mt-3 comment" id=${comment.rno}>
                        <div class="col-1 comment-replyer" >${comment.replyer} </div>
                        <div class="col-9 comment-reply" style="word-break: break-all"> ${comment.reply}</div>
                        <small class="d-block col-1" >
                            <span> ${replyService.displayTime(comment.replyDate)} </span>
                        </small>
                        <div class="col-1">
                        <i class="bi bi-x-square mr-1 commentBtn" data-oper = "deleteComment"  > </i>
                        <i class="bi bi-pencil-square commentBtn" data-oper = "modifyComment"></i>
                        </div>
                         
                      </div>`
                $("#comments").append(el);
            })
            showReplyPagenation(replyCnt, page);

        }
    )
}

function showReplyPagenation(replyCnt, currentPage){

    console.log(currentPage)
    let lastPage = Math.ceil(1 / 10.0) * 10;
    let firstPage = lastPage - 9;

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
            <a class="page-link" href="#" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>`

    if (next) nextBtn =
        `<li class="page-item">
            <a class="page-link" href="#" aria-label="Next">
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
    $("#replyPage").append(el);

}

function pageClick(){
    const targetPage = $(this).data("page");
    const bno = $("#bno").val();
    console.log(targetPage)
    console.log(bno)
    $("#comments").empty();
    $("#replyPage").empty();
    fetchComments2(bno, targetPage)

}

function commentClick() {

    const oper = $(this).data("oper");
    const replyer = $(this).parents(".comment").find(".comment-replyer").text();
    const reply = $(this).parents(".comment").find(".comment-reply").text();
    const rno = $(this).parents(".comment").attr("id");

    $("#rno").val(rno);
    $("#reply-modal").val(reply);
    $("#replyer-modal").val(replyer);

    if (oper === "deleteComment") {
        const delChk = confirm("삭제하시겠습니까?");
        if (delChk){
            replyService.remove(rno, function (res) {
                location.reload();
            })
        }
    }
    else if (oper === "modifyComment") {
        $("#exampleModal").modal("show");
    }
    else if (oper === "submit-modify") {
        const formData = $("#editCommentForm").serializeObject();

        replyService.modify(formData, function (res) {
            location.reload();
        })
    }
}