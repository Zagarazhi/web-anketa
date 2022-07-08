package ru.zagarazhi.entities.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zagarazhi.entities.domain.Answer;

@Data
@NoArgsConstructor
public class AnswerDto {

    @NotEmpty
    private Long questionId;

    @NotEmpty
    private String answer;

    private int rating;

    public AnswerDto(Answer answer) {
        this.questionId = answer.getQuestion().getId();
        this.answer = answer.getAnswer();
        this.rating = answer.getRating();
    }
}
