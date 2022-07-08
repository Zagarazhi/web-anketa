package ru.zagarazhi.entities.domain;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "questions")
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Column(length = 256)
    private String text;

    @Column(length = 32)
    private String correctAnswer;

    @ElementCollection
    private List<String> options;

    @ManyToOne(fetch = FetchType.EAGER)
    private Test test;

    @Column
    private boolean multipleChoice;
}
