package com.board.mapper;

import com.board.domain.BoardVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class BoardMapperTests {
    @Setter(onMethod_ = {@Autowired})
    private BoardMapper mapper;


    @Test
    public void getListTest(){
        mapper.getList().forEach(board -> log.info(board));
    }

    @Test
    public void insertTest(){
        BoardVO board = new BoardVO();
        board.setTitle("test title1");
        board.setContent("testContent");
        board.setWriter("testWriter");
        mapper.insert(board);

        log.info(board);
    }

    @Test
    public void insertSelectKeyTest(){
        BoardVO board = new BoardVO();
        board.setTitle("test title2");
        board.setContent("testContent2");
        board.setWriter("testWriter2");
        mapper.insertSelectKey(board);
        log.info(board);
    }

    @Test
    public void getBoardTest(){
        BoardVO board = mapper.getBoard(5l);
        log.info(board);

    }

    @Test
    public void deleteTest(){
        int deletedcnt = mapper.deleteBoard(5l);
        log.info(deletedcnt);
    }
}
