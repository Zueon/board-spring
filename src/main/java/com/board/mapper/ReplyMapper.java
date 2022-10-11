package com.board.mapper;


import com.board.domain.Criteria;
import com.board.domain.ReplyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyMapper {
    // 외래키를 사용하여 답글을 등록한다.
    public int insert(ReplyVO replyVO);

    // 특정 댓글 읽기
    public ReplyVO read(Long rno);

    public int delete(Long rno);

    public int update(ReplyVO replyVO);

    // xml로 처리할 때 지정된 두 개의 파라미터를 모두 사용할 수 있다
    public List<ReplyVO> getListWithPaging(@Param("cri")Criteria cri, @Param("bno")Long bno);
}
