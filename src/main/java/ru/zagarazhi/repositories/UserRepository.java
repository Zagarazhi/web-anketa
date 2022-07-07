package ru.zagarazhi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.zagarazhi.entities.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
