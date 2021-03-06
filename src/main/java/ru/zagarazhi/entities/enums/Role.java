package ru.zagarazhi.entities.enums;

import org.springframework.security.core.GrantedAuthority;

//Роли
public enum Role implements GrantedAuthority{
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority(){
        return name();
    }
}
