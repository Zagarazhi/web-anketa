package ru.zagarazhi.controllers;

import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public interface MainController {
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code);

    @GetMapping("/login")
    public String login(Model model);

    @GetMapping("/tests")
    public String tests();

    @GetMapping("/test")
    public String test(@Param("id") long id);

    @GetMapping("/results")
    public String results(@Param("id") long id);

    @GetMapping("/users")
    public String users();

    @GetMapping("/admin/create")
    public String create();

    @GetMapping("/admin/results")
    public String adminResults(@Param("uId") long uId, @Param("id") long id);

    @GetMapping("/admin/users")
    public String adminUsers();
}
