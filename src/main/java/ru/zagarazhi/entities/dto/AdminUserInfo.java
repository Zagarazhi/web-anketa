package ru.zagarazhi.entities.dto;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import lombok.Data;
import ru.zagarazhi.entities.domain.User;

@Data
//Инфо о пользователе для админа
public class AdminUserInfo {
    private long id;
    private long rating;
    private String username;
    private String email;
    private List<AnsweredTestDto> answeredTestDtos;

    public AdminUserInfo(User user){
        this.id = user.getId();
        this.rating = user.getRating();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.answeredTestDtos = user.getTests().stream().filter(distinctByKey(t -> t.getTest().getId())).map(t -> new AnsweredTestDto(t)).toList();
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
