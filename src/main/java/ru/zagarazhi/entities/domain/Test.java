package ru.zagarazhi.entities.domain;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "tests")
public class Test {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "test")
    private List<Question> questions;
}
