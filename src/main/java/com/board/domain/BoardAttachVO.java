package com.board.domain;

import lombok.Data;

@Data
public class BoardAttachVO {
    private String filename;        // 원본 파일의 이름
    private String uploadPath;    // 업로드된 경로
    private String uuid;            // uuid 값
    private boolean fileType;
    private Long bno;
}
