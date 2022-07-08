package ru.zagarazhi.entities.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zagarazhi.entities.domain.AnsweredTest;

@Data
@NoArgsConstructor
public class AnsweredTestDto {

    @NotEmpty
    private Long testId;

    @NotEmpty
    private AnswerDto[] answers;

    private int attempt;

    private Long rating;

    public AnsweredTestDto(AnsweredTest answeredTest) {
        this.testId = answeredTest.getTest().getId();
        this.answers = (@NotEmpty AnswerDto[]) answeredTest.getAnswers().stream().map(t -> new AnswerDto(t)).toArray();
        this.attempt = answeredTest.getAttempt();
        this.rating = answeredTest.getRating();
    }
}
