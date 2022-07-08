package ru.zagarazhi.entities.dto;

import java.util.Arrays;

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
    private String[] options;

    @NotEmpty
    private boolean multipleChoice;

    public QuestionDto(Question question) {
        this.id = question.getId();
        this.text = question.getText();
        this.correctAnswer = question.getCorrectAnswer();
        this.multipleChoice = question.isMultipleChoice();
        this.options = (@NotEmpty String[]) question.getOptions().toArray();
    }

    public Question getQuestion(){
        Question question = new Question();
        question.setText(this.text);
        question.setCorrectAnswer(this.correctAnswer);
        question.setOptions(Arrays.asList(this.options));
        question.setMultipleChoice(this.multipleChoice);
        return question;
    }
}
