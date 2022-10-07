package com.ze.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("data", "Hello, Spring from IntelliJ! :)");
        return "index";
    }
}

