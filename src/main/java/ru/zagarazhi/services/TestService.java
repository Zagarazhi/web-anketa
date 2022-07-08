package ru.zagarazhi.services;

import java.util.List;

import ru.zagarazhi.entities.dto.AnsweredTestDto;
import ru.zagarazhi.entities.dto.ResultsDto;
import ru.zagarazhi.entities.dto.TestDto;
import ru.zagarazhi.entities.dto.TestName;

public interface TestService {
    public boolean save(TestDto testDto);
    public List<TestName> names();
    public ResultsDto results(long id);
    public ResultsDto results(long id, long userId);
    public TestDto findTestById(long id);
    public boolean saveAnswers(AnsweredTestDto answeredTestDto);
}
