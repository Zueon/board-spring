package com.board.mapper;

import com.board.domain.BoardAttachVO;

import java.util.List;

public interface BoardAttachMapper {
    public void insert(BoardAttachVO attachment);
    public void delete(String uuid);
    public List<BoardAttachVO> findByBno(Long bno);
}
