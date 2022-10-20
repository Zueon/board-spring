package com.board.service;

import com.board.domain.BoardAttachVO;
import com.board.domain.BoardVO;
import com.board.domain.Criteria;

import java.util.List;

public interface BoardService {
    public void register(BoardVO board);

    public BoardVO get(Long bno);

    public boolean modify(BoardVO board);

    public boolean remove(Long bno);

//    public List<BoardVO> getList();
    public List<BoardVO> getList(Criteria cri);

    public List<BoardAttachVO> getAttachments(Long bno);

    public int getTotalBoardCnt(Criteria cri);




}
