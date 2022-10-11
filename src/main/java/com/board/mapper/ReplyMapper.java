package com.board.mapper;


import com.board.domain.ReplyVO;

public interface ReplyMapper {
    // 외래키를 사용하여 답글을 등록한다.
    public int insert(ReplyVO replyVO);

    // 특정 댓글 읽기
    public ReplyVO read(Long rno);

    public int delete(Long rno);

    public int update(ReplyVO replyVO);
}
