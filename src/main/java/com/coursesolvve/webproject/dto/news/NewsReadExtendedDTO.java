package com.coursesolvve.webproject.dto.news;

import com.coursesolvve.webproject.dto.contentmanager.ContentManagerReadDTO;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class NewsReadExtendedDTO {
    private UUID id;

    private ContentManagerReadDTO contentManager;

    private Instant createdAt;
    private Instant updatedAt;

    private String info;
    private Boolean newsMistake;
    private Integer likeRating;
}
