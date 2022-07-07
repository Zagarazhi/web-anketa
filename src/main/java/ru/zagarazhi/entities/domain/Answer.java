package ru.zagarazhi.entities.domain;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "answers")
public class Answer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @Column(length = 32)
    private String answer;

    @ManyToOne(fetch = FetchType.EAGER)
    private Question question;

    @ManyToOne(fetch = FetchType.EAGER)
    private AnsweredTest answeredTest;
}
