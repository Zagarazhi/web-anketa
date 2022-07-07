package ru.zagarazhi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.zagarazhi.entities.domain.AnsweredTest;

public interface AnsweredTestRepository extends JpaRepository<AnsweredTest, Long> {
    
}
