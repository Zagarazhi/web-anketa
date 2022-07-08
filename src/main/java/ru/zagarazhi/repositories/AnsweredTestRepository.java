package ru.zagarazhi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.zagarazhi.entities.domain.AnsweredTest;
import ru.zagarazhi.entities.domain.Test;

public interface AnsweredTestRepository extends JpaRepository<AnsweredTest, Long> {
    public List<AnsweredTest> findByTest(Test test);
}
