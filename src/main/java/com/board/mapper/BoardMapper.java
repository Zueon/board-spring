package com.board.mapper;

import com.board.domain.BoardVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BoardMapper {
//    @Select ( "select * from tbl_board where bno > 0")
    public List<BoardVO> getList();
}
