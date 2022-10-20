package com.board.controller;

import com.board.domain.BoardAttachVO;
import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.domain.PageDTO;
import com.board.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
    private BoardService service;

//    @GetMapping("/list")
//    public void list(Model model){
//        log.info("----------------- list -----------------");
//        model.addAttribute("list", service.getList());
//    }

    @GetMapping("/list")
    public void list(Criteria cri, Model model){

        model.addAttribute("list", service.getList(cri));
        // 페이징을 위한 관련 내용 정보를 전달한다.
        int total = service.getTotalBoardCnt(cri);
        PageDTO pageInfo = new PageDTO(cri, total);
        model.addAttribute("pageInfo", pageInfo);

    }

    @GetMapping("/register")
    public void list(){
        log.info("----------------- register -----------------");
    }


    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam("bno")Long bno, Model model, Criteria cri){
        log.info("/get or /modify");
        model.addAttribute("board", service.get(bno));
        model.addAttribute("cri", cri);
    }

    @GetMapping(value = "/getAttachments", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<BoardAttachVO>> getAttachments(Long bno) {
        log.info("BoardController ----------getAttachments by bno" + bno);
        return new ResponseEntity<>(service.getAttachments(bno), HttpStatus.OK);
    }


    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr){
        log.info("register : " + board);
        service.register(board);
        rttr.addFlashAttribute("result",board.getBno());

        if (board.getAttachList() != null) {
            board.getAttachList().forEach(boardAttachVO -> log.info(boardAttachVO));
        }

        return "redirect:/board/list";
    }


    @PostMapping("/modify")
    public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr){
        log.info("modify : " + board);
        if (service.modify(board)) rttr.addFlashAttribute("result", "success");

//        rttr.addAttribute("pageNum", cri.getPageNum());
        return "redirect:/board/list" + cri.getListLink();
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno")Long bno, Criteria cri, RedirectAttributes rttr) {
        log.info("modify : " + bno);
        if (service.remove(bno)) rttr.addFlashAttribute("result","success" );
        rttr.addAttribute("pageNum", cri.getPageNum());
        return "redirect:/board/list" +  cri.getListLink();

    }

}
