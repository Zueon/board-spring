package com.board.service;

import com.board.domain.Criteria;
import com.board.domain.ReplyVO;
import com.board.mapper.ReplyMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class ReplyServiceImpl implements ReplyService{
    @Setter(onMethod_ = @Autowired)
    private ReplyMapper mapper;

    @Override
    public int register(ReplyVO replyVO) {
        log.info("------------------ REGISTER REPLY ------------------");
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

    @Override
    public int remove(Long rno) {
        return mapper.delete(rno);
    }

    @Override
    public List<ReplyVO> getList(Criteria cri, Long bno) {
        return mapper.getListWithPaging(cri, bno);
    }
}
