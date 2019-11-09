package com.newvision.springstart.controller;

import com.newvision.springstart.dao.AppUserRepository;
import com.newvision.springstart.entity.AppUser;
import com.newvision.springstart.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private AppUserService userService;

    @GetMapping("/showCustomLoginPage")
    public String showMyLoginPage() {
        return "custom-login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

    @GetMapping("/registrationForm")
    public String showRegistrationForm(Model theModel) {
        AppUser theUser = new AppUser();
        theModel.addAttribute("theUser", theUser);
        return "registration-form";
    }

    @PostMapping("/registrationProcess")
    public String peformRegistration(@Valid @ModelAttribute("theUser") AppUser theUser,
                                     RedirectAttributes model,
                                     Errors errors){
        if (errors.hasErrors()) {
            return "registrationForm";
        }
        userService.save(theUser);
        return "custom-login";
    }
}
