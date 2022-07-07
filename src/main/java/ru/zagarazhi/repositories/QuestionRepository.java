package ru.zagarazhi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.zagarazhi.entities.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{
    
}
