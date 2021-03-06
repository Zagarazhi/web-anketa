package ru.zagarazhi.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ru.zagarazhi.entities.domain.Answer;
import ru.zagarazhi.entities.domain.AnsweredTest;
import ru.zagarazhi.entities.domain.Question;
import ru.zagarazhi.entities.domain.Test;
import ru.zagarazhi.entities.domain.User;
import ru.zagarazhi.entities.dto.AnswerDto;
import ru.zagarazhi.entities.dto.AnsweredTestDto;
import ru.zagarazhi.entities.dto.ResultsDto;
import ru.zagarazhi.entities.dto.TestDto;
import ru.zagarazhi.entities.dto.TestName;
import ru.zagarazhi.repositories.AnswerRepository;
import ru.zagarazhi.repositories.AnsweredTestRepository;
import ru.zagarazhi.repositories.QuestionRepository;
import ru.zagarazhi.repositories.TestRepository;
import ru.zagarazhi.repositories.UserRepository;
import ru.zagarazhi.services.TestService;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnsweredTestRepository answeredTestRepository;

    @Override
    public boolean save(TestDto testDto) {
        if(authCheck() == null) {
            return false;
        }
        Test test = new Test();
        List<Question> questions = new ArrayList<>();
        questions = testDto.getQuestions().stream().map(q -> q.getQuestion()).toList();

        test.setName(testDto.getName());
        test.setQuestions(questions);
        test.setMaxAttempt(testDto.getMaxAttempt());
        testRepository.save(test);

        questions.stream().forEach(q -> q.setTest(test));
        questionRepository.saveAll(questions);
        return true;
    }

    @Override
    public TestDto findTestById(long id) {
        if(authCheck() == null) {
            return null;
        }
        Optional<Test> oTest = testRepository.findById(id);
        if(oTest.isEmpty()){
            return null;
        }
        return new TestDto(oTest.get());
    }

    @Override
    public TestDto findTestByIdWithAnswerCheck(long id) {
        User user = authCheck();
        if(user == null) {
            return null;
        }
        Optional<Test> oTest = testRepository.findById(id);
        if(oTest.isEmpty()){
            return null;
        }
        Test test = oTest.get();
        if(test.getMaxAttempt() <= getMaxAttempt(test, user)) {
            return null;
        }
        test.getQuestions().stream().forEach(q -> q.setCorrectAnswer(""));
        return new TestDto(test);
    }

    @Override
    public ResultsDto results(long id) {
        User user = authCheck();
        if(user == null) {
            return null;
        }
        Optional<Test> oTest = testRepository.findById(id);
        if(oTest.isEmpty()) return null;
        Test test = oTest.get();
        AnsweredTest max = null;
        for(AnsweredTest answeredTest : answeredTestRepository.findByTestAndUser(test, user)) {
            if(max == null || max.getAttempt() < answeredTest.getAttempt()) max = answeredTest;
        }
        if(max == null) return null;
        return new ResultsDto(max);
    }

    @Override
    public ResultsDto results(long id, long userId) {
        if(authCheck() == null) {
            return null;
        }
        Optional<User> oUser = userRepository.findById(userId);
        if(oUser.isEmpty()) {
            return null;
        }
        Optional<Test> oTest = testRepository.findById(id);
        if(oTest.isEmpty()) return null;
        Test test = oTest.get();
        User user = oUser.get();
        AnsweredTest max = null;
        for(AnsweredTest answeredTest : answeredTestRepository.findByTestAndUser(test, user)) {
            if(max == null || max.getAttempt() < answeredTest.getAttempt()) max = answeredTest;
        }
        if(max == null) return null;
        return new ResultsDto(max);
    }

    @Override
    public List<TestName> names() {
        User user = authCheck();
        if(user == null) {
            return null;
        }
        List<Test> tests = testRepository.findAll();
        return tests.stream().map(t -> new TestName(t)).collect(Collectors.toList());
    }

    @Override
    public boolean saveAnswers(AnsweredTestDto answeredTestDto) {
        User user = authCheck();
        if(user == null) {
            return false;
        }

        Optional<Test> oTest = testRepository.findById(answeredTestDto.getTestId());
        if(oTest.isEmpty()) return false;
        Test test = oTest.get();
        int attempt = getMaxAttempt(test, user) + 1;
        if(attempt > test.getMaxAttempt()) return false;

        AnsweredTest answeredTest = new AnsweredTest();
        answeredTest.setTest(test);
        answeredTest.setUser(user);
        answeredTest.setAttempt(attempt);
        
        List<Answer> answers = new ArrayList<>();
        int rating = 0, tempRating = 0;
        for(AnswerDto answerDto : answeredTestDto.getAnswers()){
            Answer answer = new Answer();
            answer.setAnswer(answerDto.getAnswer());
            answer.setQuestion(questionRepository.getReferenceById(answerDto.getQuestionId()));
            tempRating = countRating(answer, answer.getQuestion());
            rating += tempRating;
            answer.setRating(tempRating);
            answers.add(answer);
        }

        if(rating < 0) rating = 0;

        answeredTest.setAnswers(answers);
        answeredTest.setRating((answeredTest.getRating() == null)? rating : answeredTest.getRating() + rating);
        answeredTestRepository.save(answeredTest);

        answers.stream().forEach(t -> t.setAnsweredTest(answeredTest));
        answerRepository.saveAll(answers);
        user.setRating((user.getRating() == null) ? rating : user.getRating() + rating);
        userRepository.save(user);
        return true;
    }

    private int countRating(Answer answer, Question question) {
        if(question.isMultipleChoice()){
            String[] temp = answer.getAnswer().split("/");
            int count = 0;
            for(String s : temp) {
                if(question.getCorrectAnswer().contains(s)){
                    count++;
                } else {
                    count --;
                }
            }
            return count;
        } else {
            return answer.getAnswer().equals(question.getCorrectAnswer()) ? 1 : -1;
        }
    }

    private int getMaxAttempt(Test test, User user){
        int attempt = 0;
        for(AnsweredTest answeredTest : answeredTestRepository.findByTestAndUser(test, user)) {
            if(attempt < answeredTest.getAttempt()) attempt = answeredTest.getAttempt();
        }
        return attempt;
    }

    private User authCheck(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> oUser = userRepository.findByUsername(auth.getName());
        if(oUser.isEmpty()){
            return null;
        }
        User user = oUser.get();
        if(!user.isEnabled()){
            return null;
        }
        return user;
    }
}
