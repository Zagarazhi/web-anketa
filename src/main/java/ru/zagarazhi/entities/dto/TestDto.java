package ru.zagarazhi.entities.dto;

import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zagarazhi.entities.domain.Test;

@Data
@NoArgsConstructor
public class TestDto {
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private QuestionDto[] questions;

    public TestDto(Test test) {
        this.id = test.getId();
        this.name = test.getName();
        this.questions = (@NotEmpty QuestionDto[]) test.getQuestions().stream().map(q -> new QuestionDto(q)).collect(Collectors.toList()).toArray();
    }
}
