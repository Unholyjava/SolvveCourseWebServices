package com.coursesolvve.webproject.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private UUID id;

    private String nickName;
    private String login;
    private boolean trust;
    private int reviewRating;
    private int activeRating;

    @Enumerated(EnumType.STRING)
    private Access access;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isTrust() {
        return trust;
    }
    public void setTrust(boolean trust) {
        this.trust = trust;
    }

    public int getReviewRating() {
        return reviewRating;
    }
    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public int getActiveRating() {
        return activeRating;
    }
    public void setActiveRating(int activeRating) {
        this.activeRating = activeRating;
    }

    public Access getAccess() {
        return access;
    }
    public void setAccess(Access access) {
        this.access = access;
    }
}
