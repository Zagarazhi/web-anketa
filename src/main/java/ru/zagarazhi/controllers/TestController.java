package ru.zagarazhi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.zagarazhi.entities.dto.AnsweredTestDto;
import ru.zagarazhi.entities.dto.ResultsDto;
import ru.zagarazhi.entities.dto.TestName;

@RequestMapping("api/v1")
public interface TestController {
    @GetMapping("/tests")
    public ResponseEntity<List<TestName>> names();

    @GetMapping("/results/{id}")
    public ResponseEntity<ResultsDto> results(@PathVariable long id);

    @PostMapping("/test/{id}")
    public String test(@PathVariable Long id, @RequestBody AnsweredTestDto answeredTestDto);
}
