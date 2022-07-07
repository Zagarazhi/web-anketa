package ru.zagarazhi.controllers.impl;

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
    public String createUser(@ModelAttribute("userForm") @Valid UserRegistrationDto userDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "registration";
        }
        if(!userService.save(userDto)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/game";
    }
}
