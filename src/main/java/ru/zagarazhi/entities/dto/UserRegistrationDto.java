package ru.zagarazhi.entities.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import ru.zagarazhi.constraints.FieldMatch;

//Сущность для регистрации пользователя
@Data
@FieldMatch(first = "password", second = "passwordConfirm", message = "Пароли не совпадают")
public class UserRegistrationDto {
    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String passwordConfirm;
}
