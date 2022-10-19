package com.board.controller;


import com.board.domain.Criteria;
import com.board.domain.ReplyPageDTO;
import com.board.domain.ReplyVO;
import com.board.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/replies/")
@Log4j2
@AllArgsConstructor
public class ReplyController {

    private ReplyService service;

    @PostMapping(value = "/new",
            consumes = "application/json",
            produces = {MediaType.TEXT_PLAIN_VALUE}
    )
    public ResponseEntity<String> create(@RequestBody ReplyVO replyVO){
        log.info("REPLYVO : " + replyVO);
        int resultint = service.register(replyVO);
        return resultint == 1
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);


    }

//    @GetMapping(value = "/pages/{bno}/{page}",
//            produces = {"application/json"}
//    )
//    public ResponseEntity<List<ReplyVO>> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno){
//        log.info("---------------------------- getList ----------------------------");
//        Criteria cri = new Criteria(page, 10);
//        return new ResponseEntity<>(service.getList(cri,bno), HttpStatus.OK);
//    }
//

    @GetMapping(value = "/pages/{bno}/{page}",
            produces = {"application/json", }
    )
    public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno){
        log.info("---------------------------- getList ----------------------------");
        Criteria cri = new Criteria(page, 10);
        return new ResponseEntity<>(service.getListPage(cri,bno), HttpStatus.OK);
    }


    @GetMapping(value = "/{rno}",
            produces = {"application/json"}
    )
    public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
        log.info("---------------------------- get ----------------------------");

        return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String > delete(@PathVariable("rno") Long rno) {
        return service.remove(rno) == 1
                ? new ResponseEntity<>("Success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "/{rno}", consumes = "application/json", produces={MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> modify(@PathVariable("rno") Long rno, @RequestBody ReplyVO replyVO){
        replyVO.setRno(rno);
        return service.modify(replyVO) == 1
                ? new ResponseEntity<>("Success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
