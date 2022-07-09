package ru.zagarazhi.entities.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import ru.zagarazhi.entities.domain.AnsweredTest;

@Data
//ДТО для результатов
public class ResultsDto {
    private List<String> userAnswers;
    private List<String> correctAnswers;
    private List<Integer> ratings;
    private long rating;

    public ResultsDto(AnsweredTest answeredTest) {
        if(answeredTest.getTest().getMaxAttempt() <= answeredTest.getAttempt()) {
            this.correctAnswers = answeredTest.getTest().getQuestions().stream().map(q -> q.getCorrectAnswer()).collect(Collectors.toList());
        }
        this.ratings = answeredTest.getAnswers().stream().map(a -> a.getRating()).collect(Collectors.toList());
        this.userAnswers = answeredTest.getAnswers().stream().map(a -> a.getAnswer()).collect(Collectors.toList());
        this.rating = answeredTest.getRating();
    }
}
