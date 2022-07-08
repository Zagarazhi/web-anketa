package ru.zagarazhi.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import ru.zagarazhi.controllers.MainController;
import ru.zagarazhi.services.UserService;

@Controller
public class MainControllerImpl implements MainController{

    @Autowired
    private UserService userService;
    
    @Override
    public String verifyUser(@Param("code") String code) {
        if (userService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

    @Override
    public String login(Model model) {
        return "login";
    }

    @Override
    public String loginFail(Model model){
        return "login_fail";
    }

    @Override
    public String root(Model model) {
        return "index";
    }
}
