package com.coursesolvve.webproject.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class ClientReadDTO {
    private UUID id;
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
