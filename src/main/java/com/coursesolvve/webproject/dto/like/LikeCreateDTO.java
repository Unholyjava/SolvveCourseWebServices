package com.coursesolvve.webproject.dto.like;

import com.coursesolvve.webproject.domain.LikedObjectType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class LikeCreateDTO {
    private Boolean like;
    private UUID likedObjectId;
    private LikedObjectType likeType;
}
