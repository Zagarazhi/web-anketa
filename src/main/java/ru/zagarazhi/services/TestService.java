package ru.zagarazhi.services;

import ru.zagarazhi.entities.dto.TestDto;

public interface TestService {
    public boolean save(TestDto testDto);
    public TestDto findTestById(long id);
}
