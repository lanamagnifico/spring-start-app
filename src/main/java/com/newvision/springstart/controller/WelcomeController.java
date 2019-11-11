package com.newvision.springstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class WelcomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String showWelcomePage(Model theModel) {
        theModel.addAttribute("message_mvc","Hello, Spring MVC and REST!");
        return "welcome";
    }
}
