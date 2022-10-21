package com.board.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequestMapping("/sample/*")
@Controller
public class SampleController {

    @GetMapping("/all")
    public void doAll(){
        log.info("SampleController - doAll can access everyone");
    }

    @GetMapping("/member")
    public void doMember(){
        log.info("SampleController - doMember can access member");

    }

    @GetMapping("/admin")
    public void doAdmin(){
        log.info("SampleController - admin");

    }
}

