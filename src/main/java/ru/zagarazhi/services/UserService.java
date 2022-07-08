package ru.zagarazhi.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ru.zagarazhi.entities.domain.User;
import ru.zagarazhi.entities.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
    
    public BCryptPasswordEncoder passwordEncoder();

    public Optional<User> findByUsername(String username);

    public boolean save(UserRegistrationDto registration, String siteUrl);

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    public boolean verify(String verificationCode);
}
