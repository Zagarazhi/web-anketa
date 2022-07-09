package ru.zagarazhi.entities.dto;

import lombok.Data;
import ru.zagarazhi.entities.domain.User;

@Data
//Инфо о пользователе для простых пользователей
public class UserInfo {
    private long rating;
    private String username;

    public UserInfo(User user){
        this.rating = user.getRating();
        this.username = user.getUsername();
    }
}
