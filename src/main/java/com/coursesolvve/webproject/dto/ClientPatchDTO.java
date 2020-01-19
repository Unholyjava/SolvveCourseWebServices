package com.coursesolvve.webproject.dto;

import lombok.Data;

@Data
public class ClientPatchDTO {
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
