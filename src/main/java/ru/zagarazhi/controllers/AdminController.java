package ru.zagarazhi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonParseException;

import ru.zagarazhi.entities.dto.AdminUserInfo;
import ru.zagarazhi.entities.dto.ResultsDto;
import ru.zagarazhi.entities.dto.TestDto;

@RequestMapping("/api/v1/admin")
public interface AdminController {
    
    @PostMapping("/save")
    public String save(@RequestBody TestDto testDto) throws JsonParseException; 

    @GetMapping("/results/{uId}/{id}")
    public ResponseEntity<ResultsDto> results(@PathVariable long uId, @PathVariable long id);

    @GetMapping("/users")
    public ResponseEntity<List<AdminUserInfo>> findAll();
}
