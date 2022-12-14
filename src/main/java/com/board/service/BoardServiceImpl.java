package com.board.service;

import com.board.domain.BoardAttachVO;
import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.mapper.BoardAttachMapper;
import com.board.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
    @Setter(onMethod_ = {@Autowired})
    private BoardMapper mapper;

    @Setter(onMethod_ = {@Autowired})
    private BoardAttachMapper attachMapper;

    @Transactional
    @Override
    public void register(BoardVO board) {
        log.info("---------------- BoardServiceImpl register ----------------" + board);
        mapper.insertSelectKey(board);
        if (board.getAttachList() == null || board.getAttachList().size() == 0) return;

        board.getAttachList().forEach(attach -> {
            attach.setBno(board.getBno());
            attachMapper.insert(attach);
        });
    }

    @Override
    public BoardVO get(Long bno) {
        log.info("get...............: " + bno);
        return mapper.read(bno);
    }

    @Override
    public boolean modify(BoardVO board) {
        log.info("---------------- BoardServiceImpl modify ----------------" + board);
        attachMapper.deleteAll(board.getBno());
        boolean modifyRes = mapper.update(board) == 1;

        if (modifyRes && board.getAttachList() != null && board.getAttachList().size() > 0) {
            board.getAttachList().forEach(attach -> {
                attach.setBno(board.getBno());
                attachMapper.insert(attach);
            });
        }

        return modifyRes;
    }

    @Transactional
    @Override
    public boolean remove(Long bno) {
        log.info("---------------- BoardServiceImpl remove ----------------" + bno);
        attachMapper.deleteAll(bno);
        return mapper.delete(bno) == 1;
    }

//    @Override
//    public List<BoardVO> getList() {
//        log.info("getList..............");
//        return mapper.getList();
//    }

    @Override
    public List<BoardVO> getList(Criteria cri) {
        return mapper.getListWithPaging(cri);
    }

    @Override
    public int getTotalBoardCnt(Criteria cri) {
        log.info("get total board posts cnt from DB");
        return mapper.getTotalBoardCnt(cri);
    }

    @Override
    public List<BoardAttachVO> getAttachments(Long bno) {
        log.info("boardService ----- getAttachments ");
        return attachMapper.findByBno(bno);
    }
}
