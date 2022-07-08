package ru.zagarazhi.entities.dto;

import java.util.List;

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
    private List<AnswerDto> answers;

    private int attempt;

    private Long rating;

    public AnsweredTestDto(AnsweredTest answeredTest) {
        this.testId = answeredTest.getTest().getId();
        this.answers = answeredTest.getAnswers().stream().map(t -> new AnswerDto(t)).toList();
        this.attempt = answeredTest.getAttempt();
        this.rating = answeredTest.getRating();
    }
}
