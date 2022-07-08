package ru.zagarazhi.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import ru.zagarazhi.controllers.UserController;
import ru.zagarazhi.entities.dto.UserInfo;
import ru.zagarazhi.services.UserService;

@RestController
public class UserControllerImpl implements UserController {
    
    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<List<UserInfo>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}
