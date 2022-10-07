package com.board.mapper;

import com.board.domain.BoardVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BoardMapper {
//    @Select ( "select * from tbl_board where bno > 0")
    public List<BoardVO> getList();

    public void insert(BoardVO board);          // insert만 처리
    public void insertSelectKey (BoardVO board);// insert 처리 후 생성된 pk 값 필요 시

    public BoardVO read(long bno);

    public int delete(long bno);

    public int update(BoardVO board);
}
