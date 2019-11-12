package com.newvision.springstart.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class WelcomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String showWelcomePage(Model theModel) {
        theModel.addAttribute("message_mvc","Hello, Spring MVC! Time on server is " + LocalDateTime.now());
        return "welcome";
    }
}
