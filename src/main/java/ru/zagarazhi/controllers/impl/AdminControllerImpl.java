package ru.zagarazhi.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;

import ru.zagarazhi.controllers.AdminController;
import ru.zagarazhi.entities.dto.AdminUserInfo;
import ru.zagarazhi.entities.dto.ResultsDto;
import ru.zagarazhi.entities.dto.TestDto;
import ru.zagarazhi.services.TestService;
import ru.zagarazhi.services.UserService;

@RestController
public class AdminControllerImpl implements AdminController {

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    @Override
    public String save(TestDto testDto) throws JsonParseException {
        if(testService.save(testDto)){
            return "save_success";
        }
        return "save_fail";
    }

    @Override
    public ResponseEntity<ResultsDto> results(@PathVariable long uId, long id) {
        ResultsDto resultsDto = testService.results(id, uId);
        if(resultsDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(resultsDto);
    }

    @Override
    public ResponseEntity<List<AdminUserInfo>> findAll(){
        return ResponseEntity.ok(userService.adminFindAll());
    }
}
