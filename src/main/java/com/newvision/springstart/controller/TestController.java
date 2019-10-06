package com.newvision.springstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model theModel) {
        theModel.addAttribute("message","Spring MVC works!");
        return "test";
    }
}
