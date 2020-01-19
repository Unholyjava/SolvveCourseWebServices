package com.coursesolvve.webproject.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ClientCreateDTO {
    private String nickName;
    private String login;
    private String mail;
    private String name;
    private String patronymic;
    private String surname;
    private boolean trust;
    private int reviewRating;
    private int activeRating;
    private boolean isBlock;
}
