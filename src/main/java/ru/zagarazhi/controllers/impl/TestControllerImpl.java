package ru.zagarazhi.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.zagarazhi.controllers.TestController;
import ru.zagarazhi.entities.dto.AnsweredTestDto;
import ru.zagarazhi.entities.dto.ResultsDto;
import ru.zagarazhi.entities.dto.TestDto;
import ru.zagarazhi.entities.dto.TestName;
import ru.zagarazhi.services.TestService;

@RestController
public class TestControllerImpl implements TestController {

    @Autowired
    private TestService testService;

    @Override
    public ResponseEntity<List<TestName>> names() {
        List<TestName> names = testService.names();
        if(names == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        return ResponseEntity.ok(names);
    }

    @Override
    public String test(@PathVariable Long id, @RequestBody AnsweredTestDto answeredTestDto){
        testService.saveAnswers(answeredTestDto);
        return "/tests";
    }

    @Override
    public ResponseEntity<ResultsDto> results(@PathVariable long id) {
        ResultsDto resultsDto = testService.results(id);
        if(resultsDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(resultsDto);
    }

    @Override
    public ResponseEntity<TestDto> test(@PathVariable long id) {
        TestDto test = testService.findTestByIdWithAnswerCheck(id);
        if(test == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        return ResponseEntity.ok(test);
    }
}
