package ru.zagarazhi.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ru.zagarazhi.entities.domain.Question;
import ru.zagarazhi.entities.domain.Test;
import ru.zagarazhi.entities.domain.User;
import ru.zagarazhi.entities.dto.TestDto;
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

    @Override
    public boolean save(TestDto testDto) {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //User user = userRepository.findByUsername(auth.getName()).get();
        //if(!user.isEnabled()){
            //return false;
        //}
        //Test test = testDto.getTest();
        Test test = new Test();
        List<Question> questions = new ArrayList<>();
        questions = Arrays.stream(testDto.getQuestions()).map(q -> q.getQuestion()).collect(Collectors.toList());

        test.setName(testDto.getName());
        test.setQuestions(questions);
        testRepository.save(test);

        questions.stream().forEach(q -> q.setTest(test));
        questionRepository.saveAll(questions);
        return true;
    }

    @Override
    public TestDto findTestById(long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName()).get();
        if(!user.isEnabled()){
            return null;
        }
        Optional<Test> oTest = testRepository.findById(id);
        if(oTest.isEmpty()){
            return null;
        }
        return new TestDto(oTest.get());
    }
}
