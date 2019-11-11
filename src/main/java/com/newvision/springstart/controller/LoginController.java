package com.newvision.springstart.controller;

import com.newvision.springstart.entity.User;
import com.newvision.springstart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/showCustomLoginPage")
    public String showMyLoginPage(Model theModel) {
        User theUser = new User();
        theModel.addAttribute("theUser", theUser);
        return "custom-login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

    @PostMapping("/registrationProcess")
    public String peformRegistration(@Valid @ModelAttribute("theUser") User theUser,
                                     RedirectAttributes model,
                                     Errors errors){
        if (errors.hasErrors()) {
            return "custom-login";
        }
        userService.save(theUser);
        return "redirect:/showCustomLoginPage";
    }
}
