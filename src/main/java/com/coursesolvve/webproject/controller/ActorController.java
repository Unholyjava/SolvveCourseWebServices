package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.dto.ActorCreateDTO;
import com.coursesolvve.webproject.dto.ActorPatchDTO;
import com.coursesolvve.webproject.dto.ActorReadDTO;
import com.coursesolvve.webproject.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/{id}")
    public ActorReadDTO getActor(@PathVariable UUID id) {
        return actorService.getActor(id);
    }

    @PostMapping
    public ActorReadDTO createActor(@RequestBody ActorCreateDTO createDTO) {
        return actorService.createActor(createDTO);
    }

    @PatchMapping("/{id}")
    public ActorReadDTO patchActor(@PathVariable UUID id, @RequestBody ActorPatchDTO patch) {
        return actorService.patchActor(id, patch);
    }

    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable UUID id) {
        actorService.deleteActor(id);
    }
}
