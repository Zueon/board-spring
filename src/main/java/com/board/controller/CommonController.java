package com.board.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class CommonController {

    @GetMapping("/accessError")
    public void accessDenied(Authentication auth, Model model){
        log.info("access Denied : " + auth);

        model.addAttribute("msg", "Access Denied");
    }

    @GetMapping("/customLogin")
    public void loginInput(String err, String logout, Model model){
        log.info("error : " + err);
        log.info("logout : " + logout);

        if (err != null) {
            model.addAttribute("error", "Login Error");
        }

        if (logout != null) {
            model.addAttribute("logout", "Logout");
        }
    }

    @GetMapping("/customLogout")
    public void logoutGET(){
        log.info("custom logout");
    }

}
