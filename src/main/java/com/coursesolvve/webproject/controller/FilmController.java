package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.dto.FilmReadDTO;
import com.coursesolvve.webproject.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping("/{id}")
    public FilmReadDTO getFilm(@PathVariable UUID id) {
        return filmService.getFilm(id);
    }
}