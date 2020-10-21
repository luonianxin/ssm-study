package com.learn.ssm.chapter16.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class LocaleController {
    @RequestMapping("/msgpage")
    public String page(Model model){

        return "msgpage";
    }
}
