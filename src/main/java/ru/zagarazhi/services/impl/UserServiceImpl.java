package ru.zagarazhi.services.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ru.zagarazhi.entities.domain.User;
import ru.zagarazhi.entities.dto.UserRegistrationDto;
import ru.zagarazhi.entities.enums.Role;
import ru.zagarazhi.repositories.UserRepository;
import ru.zagarazhi.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> oUser = findByUsername(username);
        if(oUser.isEmpty()){
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        User user = oUser.get();
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    @Override
    public boolean save(UserRegistrationDto registration) {
        Optional<User> oUser = userRepository.findByUsername(registration.getUsername());
        if(oUser.isPresent()){
            return false;
        }
        User user = new User();
        user.setUsername(registration.getUsername());
        user.setRating((long) 0);
        user.setPassword(passwordEncoder().encode(registration.getPassword()));
        userRepository.save(user);
        return true;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getAuthority())).collect(Collectors.toList());
    }
}
