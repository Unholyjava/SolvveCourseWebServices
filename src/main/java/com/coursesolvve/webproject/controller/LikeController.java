package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.dto.like.LikeCreateDTO;
import com.coursesolvve.webproject.dto.like.LikePatchDTO;
import com.coursesolvve.webproject.dto.like.LikePutDTO;
import com.coursesolvve.webproject.dto.like.LikeReadDTO;
import com.coursesolvve.webproject.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @GetMapping("/{id}")
    public LikeReadDTO getLike(@PathVariable UUID id) {
        return likeService.getLike(id);
    }

    @PostMapping
    public LikeReadDTO createLike(@RequestBody LikeCreateDTO createDTO) {
        return likeService.createLike(createDTO);
    }

    @PatchMapping("/{id}")
    public LikeReadDTO patchLike(@PathVariable UUID id, @RequestBody LikePatchDTO patch) {
        return likeService.patchLike(id, patch);
    }

    @PutMapping("/{id}")
    public LikeReadDTO updateLike(@PathVariable UUID id, @RequestBody LikePutDTO put) {
        return likeService.updateLike(id, put);
    }

    @DeleteMapping("/{id}")
    public void deleteLike(@PathVariable UUID id) {
        likeService.deleteLike(id);
    }
}
