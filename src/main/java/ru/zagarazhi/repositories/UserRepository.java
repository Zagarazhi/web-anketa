package ru.zagarazhi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.zagarazhi.entities.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
    public Optional<User> findByUsername(String username);
    public Optional<User> findByVerificationCode(String code);
}
