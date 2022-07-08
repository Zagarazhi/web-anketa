package ru.zagarazhi.entities.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import ru.zagarazhi.entities.domain.User;

@Data
public class AdminUserInfo {
    private long rating;
    private String username;
    private String email;
    private List<AnsweredTestDto> answeredTestDtos;

    public AdminUserInfo(User user){
        this.rating = user.getRating();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.answeredTestDtos = user.getTests().stream().map(t -> new AnsweredTestDto(t)).collect(Collectors.toList());
    }
}
