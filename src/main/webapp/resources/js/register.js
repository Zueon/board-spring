$(document).on('click', ".removeAttach", function (e) {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    const filename = $(this).data("file");
    const type = $(this).data("type");
    const targetEl = $(this).closest("li");
    console.log(filename, type);

    $.ajax(
        {
            url:"/deleteFile",
            data:
                {
                    filename : filename, type:type
                },
            beforeSend: function(xhr){
                xhr.setRequestHeader(header, token);
            },
            dataType : "text",
            type: "POST",
            success : function (result) {
                console.log(result);
                targetEl.remove();
            }
        }
    )
})



$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    const formObj = $("#registerForm");
    const fileInputEl = $("#attachment");

    const regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");    // 업로드 제한 파일 확장자명
    const maxSize = 5242880 // 5MB


    $(".submitBtn").click(function (e){
        e.preventDefault();
        console.log("submit clicked")

        let inputEl = "";

        $(".list-group-item").each(function(idx, file){
            console.log(idx)
            console.log(file)
            inputEl +=
                `
                <input type="hidden" name="attachList[${idx}].filename" value="${$(this).data("filename")}"/>
                <input type="hidden" name="attachList[${idx}].uuid" value="${$(this).data("uuid")}"/>
                <input type="hidden" name="attachList[${idx}].uploadPath" value="${$(this).data("uploadpath")}"/>
                <input type="hidden" name="attachList[${idx}].fileType" value="${$(this).data("type")}"/>
                `
        })
        formObj.append(inputEl);
        formObj.submit();
    })

    // 파일 선택시
    fileInputEl.change(function (e) {

        console.log(header)
        console.log(token)

        let formData = new FormData();      // 새로운 formData 객체 생성
        let files = $(this)[0].files;

        for (let i = 0; i < files.length; i++) {
            console.log(files[i]);
            // 프론트 측에서 파일 확장자와 크기를 사전처리한다.
            if (!checkExtension(files[i].name, files[i].size)) return false;
            formData.append("uploadFile", files[i]);        // formData에 uploadFile이라는 name으로 파일을 등록한다. 이 때 name은 중복가능하고 백엔드 측과 name이 동일해야한다.
        }

        $.ajax(
            {
                url: '/upload',
                processData: false,
                contentType: false,
                beforeSend: function(xhr){
                    xhr.setRequestHeader(header, token);
                },
                data: formData,
                type: 'post',
                dataType: 'json',
                success: function (result) {
                    console.log(result);
                    fetchFileList(result);
                }
            }
        )


    })

    // 파일 확장자와 크기를 사전처리
    function checkExtension(fileName, fileSize) {
        if (fileSize >= maxSize) {
            alert("too large file size");
            return false;
        }

        if (regex.test(fileName)) {
            alert("해당 파일의 종류는 업로드할 수 없습니다.")
            return false;
        }
        return true;
    }

    function fetchFileList(uploadResult) {
        let liEl = "";

        $("#attachment").val("");

        $.each(uploadResult, function (idx, file) {

            if (!file.image) {
                liEl += `
                  <li class="list-group-item" data-filename="${file.filename}" data-uuid="${file.uuid}" data-uploadPath="${file.uploadPath}" data-type="${file.image}">
                    <img src='/resources/images/file-icon.png'/>${file.filename}
                    <div class="text-right">
                      <i class="bi bi-x-circle removeAttach" data-type="file" data-file="${file.uploadPath}/${file.uuid}_${file.filename}"></i>
                    </div>
                  </li>`
            } else
                liEl += `
                <li class="list-group-item" data-filename="${file.filename}" data-uuid="${file.uuid}" data-uploadPath="${file.uploadPath}" data-type="${file.image}">
                  <img src="/display?filename=${file.uploadPath}/s_${file.uuid}_${file.filename}"/>
                  <div class="text-right">
                      <i class="bi bi-x-circle removeAttach" data-type="image" data-file="${file.uploadPath}/s_${file.uuid}_${file.filename}"></i>
                  </div>
                </li>
                `
        })
        $("#file-list").append(liEl);


    }
})