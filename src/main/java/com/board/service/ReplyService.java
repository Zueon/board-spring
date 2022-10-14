package com.board.service;

import com.board.domain.Criteria;
import com.board.domain.ReplyPageDTO;
import com.board.domain.ReplyVO;

import java.util.List;

public interface ReplyService {
    public int register(ReplyVO replyVO);
    public ReplyVO get(Long rno);
    public int modify(ReplyVO replyVO);
    public int remove(Long rno);
    public List<ReplyVO> getList(Criteria cri,Long bno);
    public ReplyPageDTO getListPage(Criteria cri, Long bno);
}
