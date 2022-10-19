package com.board.mapper;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoardMapper {
//    @Select ( "select * from tbl_board where bno > 0")
    public List<BoardVO> getList();
    public List<BoardVO> getListWithPaging(Criteria cri);

    public void insert(BoardVO board);          // insert만 처리
    public void insertSelectKey (BoardVO board);// insert 처리 후 생성된 pk 값 필요 시

    public BoardVO read(long bno);

    public int delete(long bno);

    public int update(BoardVO board);

    public int getTotalBoardCnt(Criteria cri);

    // amount : 증가 또는 감소를 의미한다. 댓글이 등록되면 1증가하고 삭제되면 1 감소
    // MyBatis의 SQL 처리 방식은 기본적으로 하나의 파라미터 타입을 이용한다. 두 개 이상 사용하려면 @Param 어노테이션이 필요함
    public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);

}
