package ru.zagarazhi.entities.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zagarazhi.entities.domain.Question;

@Data
@NoArgsConstructor
public class QuestionDto {
    private Long id;

    @NotEmpty
    private String text;

    @NotEmpty
    private String correctAnswer;

    @NotEmpty
    private List<String> options;

    @NotEmpty
    private boolean multipleChoice;

    public QuestionDto(Question question) {
        this.id = question.getId();
        this.text = question.getText();
        this.correctAnswer = question.getCorrectAnswer();
        this.multipleChoice = question.isMultipleChoice();
        this.options = question.getOptions();
    }

    public Question getQuestion(){
        Question question = new Question();
        question.setText(this.text);
        question.setCorrectAnswer(this.correctAnswer);
        question.setOptions((this.options));
        question.setMultipleChoice(this.multipleChoice);
        return question;
    }
}
