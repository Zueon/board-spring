package com.board.service;

import com.board.domain.Criteria;
import com.board.domain.ReplyPageDTO;
import com.board.domain.ReplyVO;
import com.board.mapper.BoardMapper;
import com.board.mapper.ReplyMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class ReplyServiceImpl implements ReplyService{
    @Setter(onMethod_ = @Autowired)
    private ReplyMapper mapper;

    @Setter(onMethod_ = @Autowired)
    private BoardMapper boardMapper;


    @Transactional
    @Override
    public int register(ReplyVO replyVO) {
        log.info("------------------ REGISTER REPLY ------------------");
        boardMapper.updateReplyCnt(replyVO.getBno(), 1);
        return mapper.insert(replyVO);
    }

    @Override
    public ReplyVO get(Long rno) {
        log.info("------------------ GET REPLY ------------------");
        return mapper.read(rno);
    }

    @Override
    public int modify(ReplyVO replyVO) {
        log.info("------------------ MODIFY REPLY ------------------");
        return mapper.update(replyVO);
    }

    @Transactional
    @Override
    public int remove(Long rno) {

        ReplyVO replyVO = mapper.read(rno);
        boardMapper.updateReplyCnt(replyVO.getBno(), -1);
        return mapper.delete(rno);
    }

    @Override
    public List<ReplyVO> getList(Criteria cri, Long bno) {
        return mapper.getListWithPaging(cri, bno);
    }

    @Override
    public ReplyPageDTO getListPage(Criteria cri, Long bno) {

        return new ReplyPageDTO(
                mapper.getListWithPaging(cri, bno),
                mapper.getReplyCntByBno(bno)
        );
    }
}
