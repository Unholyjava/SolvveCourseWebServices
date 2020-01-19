package com.coursesolvve.webproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Client extends Customer{

    @Id
    @GeneratedValue
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
