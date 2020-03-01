package com.coursesolvve.webproject.dto.like;

import com.coursesolvve.webproject.domain.LikedObjectType;
import lombok.Data;

import java.util.UUID;

@Data
public class LikePutDTO {
    private Boolean like;
    private UUID likedObjectId;
    private LikedObjectType likeType;
}
