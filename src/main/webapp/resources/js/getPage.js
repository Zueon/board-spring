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


function onBtnClick(cri, bno) {
    $(".btn").click(function (e) {
        const oper = $(this).data("oper");
        let nextPage = `/board/${oper}?pageNum=${cri.pageNum}&type=${cri.type}&keyword=${cri.keyword}`

        if (oper === "list") {
            self.location = nextPage;
        } else if (oper === "modify") {
            self.location = nextPage + `&bno=${bno}`
        } else if (oper === "add") {
            e.preventDefault();
            const formData = $("#commentForm").serializeObject();
            replyService.add(formData, function () {
                $("#commentForm")[0].reset();
                $("#comments").empty();
                fetchComments(formData.bno);
            })
        }
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

    } else if (oper === "modifyComment") {
        $("#exampleModal").modal("show");

    } else if (oper === "submit-modify") {
        const formData = $("#editCommentForm").serializeObject();

        replyService.modify(formData, function (res) {
            location.reload();
        })
    }
}