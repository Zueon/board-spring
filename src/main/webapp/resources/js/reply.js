console.log("reply.js loaded");

const replyService = (function(){
    function add(reply, cb) {
        console.log("funtion add : add reply");

        $.ajax({
            type : 'POST',
            url : '/replies/new',
            data : JSON.stringify(reply),
            contentType : 'application/json; charset=utf-8',
            success : function (result){
                if(cb) cb(result);
            },
            error : function(){
                console.log("error");
            }
        })
    }

    function getList(param) {
        const bno = param.bno;
        const page = param.page || 1;

        $.ajax({
            type : 'GET',
            url: '/replies/pages/' + bno + '/' + page,
            success : function (data) {
                $.each(data, function(key, value) {
                    console.log(key, value);
                })
            }
        })
    }

    function remove(rno){
        $.ajax({
            type: 'delete',
            url : '/replies/' + rno,
            success : function(){
                console.log("deleted")
            }
        })
    }

    function modify(reply, cb) {
        const rno = reply.rno;
        $.ajax({
            type : 'PUT',
            url : '/replies/' + rno,
            data : JSON.stringify(reply),
            contentType : 'application/json; charset=utf-8',
            success : function (result){
                if(cb) cb(result);
            },
            error : function(){
                console.log("error");
            }
        })
    }

    function get(rno){
        $.ajax({
            type: 'GET',
            url : '/replies/' + rno,
            success : function(data){
                console.log(data)
            }
        })

    }


    return {add , getList, remove, modify, get}
})();

