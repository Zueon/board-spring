package com.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor     // list와 replyCnt를 생성자의 파라미터로 처리한다.
public class ReplyPageDTO {
    private List<ReplyVO> list;
    private int replyCnt;
}
