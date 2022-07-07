package ru.zagarazhi.entities.domain;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "answered_tests")
public class AnsweredTest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answered_test_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    List<Answer> answers;

    @OneToOne
    private User user;
}
