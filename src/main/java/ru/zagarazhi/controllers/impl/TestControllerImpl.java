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
        return "/";
    }
}
