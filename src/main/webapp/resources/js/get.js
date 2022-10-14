class Get{

    cri = {};
    bno = 0;

    constructor(cri, bno){
        console.log("GET initialized");
        this.cri = cri;
        this.bno = bno;

        this.addEvent();
        this.fetchComments()


    }


    addEvent(){
        this.onBoardBtnClick();
        this.onCommentBtnClick();
    }


    onBoardBtnClick() {
        const cri = this.cri;
        const bno = this.bno;

        $(".btn").click(function (e) {
            const oper = $(this).data("oper");
            let nextPage = `/board/${oper}?pageNum=${cri.pageNum}&type=${cri.type}&keyword=${cri.keyword}`

            if (oper === "list")
                self.location = nextPage;
            else if (oper === "modify")
                self.location = nextPage + `&bno=${bno}`

        })

    }

    onCommentBtnClick(){
        const bno = this.bno;
        $(".btn").click(function (e) {
            const oper = $(this).data("oper");
            // const formData = JSON.stringify($("#commentForm").serializeObject());
            const formData = {
                bno : bno,
                reply : $("#reply").val(),
                replyer : $("#replyer").val()
            }
            if (oper === "add") {
                e.preventDefault();
                replyService.add2(JSON.stringify(formData), function(data) {
                    $("#commentForm")[0].reset();
                    $("#comments").empty();
                })
            }
        })

    }

    fetchComments() {
        replyService.getList(
            {
                bno :this.bno,
                page: 1
            },
            (comments) => {
                $.each(comments, (idx, comment) => {
                    let el =
                        `<div class="row mt-3">
                        <div class="col-1">${comment.replyer} </div>
                        <div class="col-9 " style="word-break: break-all"> ${comment.reply}</div>
                        <small class="d-block col-1" >
                            <span> ${replyService.displayTime(comment.replyDate)} </span>
                        </small>
                        <div class="col-1">
                        <i class="bi bi-x-square mr-1 btn" data-oper="delete"></i>
                        <i class="bi bi-pencil-square btn" data-oper="modify"></i>
                         </div>
                         
                      </div>`
                    $("#comments").append(el);
                })
            }
        )
    }


}