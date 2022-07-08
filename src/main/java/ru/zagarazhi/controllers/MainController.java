package ru.zagarazhi.controllers;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;

public interface MainController {
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code);
}
