package ru.zagarazhi.controllers;

import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public interface MainController {
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code);

    @GetMapping("/login")
    public String login(Model model);

    @GetMapping("/login_fail")
    public String loginFail(Model model);

    @GetMapping("/")
    public String root(Model model);
}
