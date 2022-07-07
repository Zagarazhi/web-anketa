package ru.zagarazhi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.zagarazhi.entities.domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    
}
