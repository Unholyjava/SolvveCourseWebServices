package com.coursesolvve.webproject.dto.news;

import com.coursesolvve.webproject.dto.contentmanager.ContentManagerReadDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class NewsReadExtendedDTO {
    private UUID id;

    private ContentManagerReadDTO contentManager;;

    private String info;
    private boolean newsMistake;
    private int likeRating;
}
