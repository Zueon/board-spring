jQuery.fn.serializeObject = function() {
    var obj = null;
    try {
        if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
            var arr = this.serializeArray();
            if (arr) {
                obj = {};
                jQuery.each(arr, function() {
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


function onBtnClick(cri, bno){
    $(".btn").click(function (e) {
        const oper = $(this).data("oper");
        if (oper === "list") self.location =`/board/list?pageNum=${cri.pageNum}&type=${cri.type}&keyword=${cri.keyword}`
        else if (oper === "modify") self.location = `/board/modify?bno=${bno}&pageNum=${cri.pageNum}&type=${cri.type}&keyword=${cri.keyword}`

    })

}



function setOnSubmit(){

    $("#commentForm").submit(function(e){
        e.preventDefault();
        const url = $(this).attr("action");
        const formData = $(this).serializeObject();
        console.log(formData)

        $.ajax({
            url : url,
            type: "post",
            data: JSON.stringify(formData),
            contentType : "application/json; charset=utf-8",
            success : function(result) {
                $("#commentForm")[0].reset();
                $("#comments").empty();
                fetchComments(formData.bno);


            }
        })
    });
}


function fetchComments(bno) {
    replyService.getList(
        {
            bno,
            page : 1
        },
        (comments) => {
            $.each(comments, (idx,comment)=>{
                let el =
                    `<div class="row mt-3"> <div class="col-3 col-md-2 col-lg-1 pl-4">
                    ${comment.replyer} </div>
                           <div class="col-9 col-md-10 col-lg-11">
                           <div class="comment-show d-flex justify-content-between">
                           <div class="comment-text mb-3"> ${comment.reply}</div>
                                   <small class="d-block">
                                       <span> ${replyService.displayTime(comment.replyDate)} </span>
                                   </small>
                               </div>
                           </div>
                       </div>`
                $("#comments").append(el);
            })
        }
    )
}

