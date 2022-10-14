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


    function getList(param, cb) {
        const bno = param.bno;
        const page = param.page || 1;

        $.ajax({
            type : 'GET',
            url: '/replies/pages/' + bno + '/' + page,
            success : function (data) {
                if(cb) cb(data);

            }
        })
    }

    function remove(rno,cb){
        $.ajax({
            type: 'delete',
            url : '/replies/' + rno,
            success : function(data){
                if(cb) cb(data);
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

    // -------------- 시간 형식 변환 -----------------
    function displayTime(time) {
        const today = new Date();
        const timeGap = today.getTime() - time;

        const dateObj = new Date(time);

        let str = "";

        // 하루 이하 시간 차이가 나면 시분초를 보여준다
        if (timeGap < (1000*60*60*24)){
            const hour = dateObj.getHours();
            const minute = dateObj.getMinutes();
            const second = dateObj.getSeconds();

            return [
                (hour > 9 ? "" : "0") + hour, ":", (minute > 9 ? "" : "0") + minute, ":",(second > 9 ? "" : "0") + second
            ].join('');
        } else {    // 하루 이상 시간 차이가 나면 년월일을 보여준다
            const year = dateObj.getFullYear();
            const month = dateObj.getMonth() + 1; // Month는 제로 베아스인 것 주의
            const date = dateObj.getDate();

            return [
                year, '/',(month > 9 ? "" : "0") + month, "/",(date > 9 ? "" : "0") + date
            ].join('');
        }
    }

    return {add ,getList, remove, modify, get, displayTime}
})();

