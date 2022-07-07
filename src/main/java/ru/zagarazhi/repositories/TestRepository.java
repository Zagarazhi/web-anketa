package ru.zagarazhi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.zagarazhi.entities.domain.Test;

public interface TestRepository extends JpaRepository<Test, Long>{
    
}
