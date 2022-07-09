package ru.zagarazhi.entities.dto;

import lombok.Data;
import ru.zagarazhi.entities.domain.Test;

@Data
//название + id теста
public class TestName {
    private Long id;

    private String name;

    public TestName(Test test) {
        this.id = test.getId();
        this.name = test.getName();
    }
}
