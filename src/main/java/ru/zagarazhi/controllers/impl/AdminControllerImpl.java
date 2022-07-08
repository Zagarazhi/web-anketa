package ru.zagarazhi.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;

import ru.zagarazhi.controllers.AdminController;
import ru.zagarazhi.entities.dto.TestDto;
import ru.zagarazhi.services.TestService;

@RestController
public class AdminControllerImpl implements AdminController {

    @Autowired
    private TestService testService;

    @Override
    public String save(TestDto testDto) throws JsonParseException {
        if(testService.save(testDto)){
            return "save_success";
        }
        return "save_fail";
    }
}
