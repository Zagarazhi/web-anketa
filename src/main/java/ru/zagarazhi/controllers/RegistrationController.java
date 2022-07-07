package ru.zagarazhi.controllers;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ru.zagarazhi.entities.dto.UserRegistrationDto;

public interface RegistrationController {
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto();

    @GetMapping
    public String registration(Model model);

    @PostMapping
    public String createUser(@ModelAttribute("userForm") @Valid UserRegistrationDto userDto, BindingResult bindingResult, Model model);
}
