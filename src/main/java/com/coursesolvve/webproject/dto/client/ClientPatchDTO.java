package com.coursesolvve.webproject.dto.client;

import lombok.Data;

@Data
public class ClientPatchDTO {
    private String nickName;
    private String login;
    private String mail;
    private String name;
    private String patronymic;
    private String surname;
    private Boolean trust;
    private Integer reviewRating;
    private Integer activeRating;
    private Boolean isBlock;
}
