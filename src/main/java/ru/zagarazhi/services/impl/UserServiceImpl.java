package ru.zagarazhi.services.impl;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;
import ru.zagarazhi.entities.domain.User;
import ru.zagarazhi.entities.dto.AdminUserInfo;
import ru.zagarazhi.entities.dto.UserInfo;
import ru.zagarazhi.entities.dto.UserRegistrationDto;
import ru.zagarazhi.entities.enums.Role;
import ru.zagarazhi.repositories.UserRepository;
import ru.zagarazhi.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
     
    private void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "test@mail.ru";
        String senderName = "Web-anketa";
        String subject = "Подтвердите регистрацию";
        String content = "Уважаемый, [[name]],<br>"
                + "Нажмите на ссылку, чтобы подтвердить регистрацию:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">ПОДТВЕРДИТЬ</a></h3>"
                + "Спасибо<br>";
        
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        
        content = content.replace("[[name]]", user.getUsername());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);
        mailSender.send(message);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> oUser = findByUsername(username);
        if(oUser.isEmpty()){
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        User user = oUser.get();
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            user.isEnabled(),
            true,
            true,
            true,
            mapRolesToAuthorities(user.getRoles()));
    }

    @Override
    public boolean save(UserRegistrationDto registration, String siteUrl) {
        Optional<User> oUser = userRepository.findByUsername(registration.getUsername());
        if(oUser.isPresent()){
            return false;
        }
        User user = new User();
        user.setUsername(registration.getUsername());
        user.setEmail(registration.getEmail());
        user.setRating((long) 0);
        user.setRoles(new HashSet<>(Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN)));
        user.setPassword(passwordEncoder().encode(registration.getPassword()));
        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);
        userRepository.save(user);
        try{
            sendVerificationEmail(user, siteUrl);
        } catch (MessagingException messagingException){
            return false;
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            return false;
        }
        
        return true;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getAuthority())).collect(Collectors.toList());
    }

    @Override
    public boolean verify(String verificationCode) {
        Optional<User> oUser = userRepository.findByVerificationCode(verificationCode);
         
        if (oUser.isEmpty()) {
            return false;
        } else {
            User user = oUser.get();
            if(user.isEnabled()) {
                return false;
            }
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);
             
            return true;
        }
    }

    @Override
    public List<UserInfo> findAll() {
        return userRepository
                            .findAll()
                            .stream()
                            .sorted((u1, u2) -> u2.getRating().compareTo(u1.getRating()))
                            .map(u -> new UserInfo(u))
                            .toList();
    }

    @Override
    public List<AdminUserInfo> adminFindAll() {
        return userRepository
                            .findAll()
                            .stream()
                            .map(u -> new AdminUserInfo(u))
                            .toList();
    }

    @Override
    public UserInfo info() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> oUser = userRepository.findByUsername(auth.getName());
        if(oUser.isEmpty()){
            return null;
        }
        User user = oUser.get();
        if(!user.isEnabled()){
            return null;
        }
        return new UserInfo(user);
    }
}
