package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.dto.film.FilmCreateDTO;
import com.coursesolvve.webproject.dto.film.FilmPatchDTO;
import com.coursesolvve.webproject.dto.film.FilmPutDTO;
import com.coursesolvve.webproject.dto.film.FilmReadDTO;
import com.coursesolvve.webproject.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public FilmReadDTO createFilm(@RequestBody FilmCreateDTO createDTO) {
        return filmService.createFilm(createDTO);
    }

    @PatchMapping("/{id}")
    public FilmReadDTO patchFilm(@PathVariable UUID id, @RequestBody FilmPatchDTO patch) {
        return filmService.patchFilm(id, patch);
    }

    @PutMapping("/{id}")
    public FilmReadDTO updateFilm(@PathVariable UUID id, @RequestBody FilmPutDTO put) {
        return filmService.updateFilm(id, put);
    }

    @DeleteMapping("/{id}")
    public void deleteFilm(@PathVariable UUID id) {
        filmService.deleteFilm(id);
    }
}
