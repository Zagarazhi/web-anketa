package ru.zagarazhi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.zagarazhi.entities.dto.UserInfo;

@RequestMapping("/api/v1")
public interface UserController {
    @GetMapping("/users")
    public ResponseEntity<List<UserInfo>> findAll();

    @GetMapping("/info")
    public ResponseEntity<UserInfo> info();
}
