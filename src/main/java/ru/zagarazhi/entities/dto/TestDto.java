package ru.zagarazhi.entities.dto;

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
    private int maxAttempt;

    @NotEmpty
    private QuestionDto[] questions;

    public TestDto(Test test) {
        this.id = test.getId();
        this.name = test.getName();
        this.maxAttempt = test.getMaxAttempt();
        this.questions = (@NotEmpty QuestionDto[]) test.getQuestions().stream().map(q -> new QuestionDto(q)).toArray();
    }
}
