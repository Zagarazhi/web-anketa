package ru.zagarazhi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ru.zagarazhi.entities.domain.User;
import ru.zagarazhi.entities.dto.AdminUserInfo;
import ru.zagarazhi.entities.dto.UserInfo;
import ru.zagarazhi.entities.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
    
    public BCryptPasswordEncoder passwordEncoder();

    public Optional<User> findByUsername(String username);

    public List<UserInfo> findAll();

    public List<AdminUserInfo> adminFindAll();

    public boolean save(UserRegistrationDto registration, String siteUrl);

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    public boolean verify(String verificationCode);
}
