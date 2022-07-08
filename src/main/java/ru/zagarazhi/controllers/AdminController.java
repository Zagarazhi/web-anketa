package ru.zagarazhi.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonParseException;

import ru.zagarazhi.entities.dto.TestDto;

@RequestMapping("api/v1/admin")
public interface AdminController {
    
    @PostMapping("/save")
    public String save(@RequestBody TestDto testDto) throws JsonParseException; 
}
