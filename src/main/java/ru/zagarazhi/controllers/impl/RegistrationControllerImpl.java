package ru.zagarazhi.controllers.impl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.zagarazhi.controllers.RegistrationController;
import ru.zagarazhi.entities.dto.UserRegistrationDto;
import ru.zagarazhi.services.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationControllerImpl implements RegistrationController{
    @Autowired
    private UserService userService;

    @Override
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @Override
    public String registration(Model model){
        return "registration";
    }

    @Override
    public String createUser(@ModelAttribute("userForm") @Valid UserRegistrationDto userDto, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            return "registration_fail";
        }
        if(!userService.save(userDto, getSiteURL(request))) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration_fail";
        }
        return "/registration_success";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }  
}
