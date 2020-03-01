package com.coursesolvve.webproject.dto.like;

import com.coursesolvve.webproject.domain.LikedObjectType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class LikeReadDTO {
    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean like;
    private UUID likedObjectId;
    private LikedObjectType likeType;
}
