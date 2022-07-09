package ru.zagarazhi.entities.domain;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "answered_tests")
//Сущность для теста с ответами
public class AnsweredTest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answered_test_id")
    private Long id;

    @OneToOne
    private Test test;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "answeredTest")
    private List<Answer> answers;

    @OneToOne
    private User user;

    @Column
    private int attempt;

    @Column
    private Long rating;
}
