package com.board.mapper;

import com.board.domain.ReplyVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class ReplyMapperTests {
    @Setter(onMethod_ = @Autowired)
    private ReplyMapper mapper;

    private Long[] bnoArr = {8l,9l,10l,11l,12l};
    @Test
    public void replyMapper(){
        log.info(mapper);
    }

    @Test
    public void insertTest(){
        IntStream.rangeClosed(1, 10).forEach(i ->{
            ReplyVO replyVO = new ReplyVO();

            replyVO.setBno(bnoArr[i % 5]);
            replyVO.setReply("reply test" + i);
            replyVO.setReplyer("replyer" + i);
            mapper.insert(replyVO);

        });
    }

    @Test
    public void readTest(){
        log.info(mapper.read(5l));
    }
    
    @Test
    public void deleteTest(){
        log.info(mapper.delete(5l));
    }
}
