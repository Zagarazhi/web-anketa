package ru.zagarazhi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.zagarazhi.entities.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
