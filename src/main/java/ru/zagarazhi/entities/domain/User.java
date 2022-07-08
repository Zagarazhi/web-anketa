package ru.zagarazhi.entities.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;

import lombok.Data;
import ru.zagarazhi.entities.enums.Role;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 32, nullable = false, unique = true)
    private String username;

    @Email
    @Column(length = 80, nullable = false, unique = true)
    private String email;

    @Column(length = 80, nullable = false)
    private String password;

    @Column(nullable = false)
    private Long rating;

    @Transient
    private String passwordConfirm;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<AnsweredTest> tests;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;
    
    private boolean enabled;
}
