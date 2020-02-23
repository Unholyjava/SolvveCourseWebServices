package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.dto.contentmanager.ContentManagerCreateDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerPatchDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerPutDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerReadDTO;
import com.coursesolvve.webproject.service.ContentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/content-managers")
public class ContentManagerController {

    @Autowired
    private ContentManagerService contentManagerService;

    @GetMapping("/{id}")
    public ContentManagerReadDTO getContentManager(@PathVariable UUID id) {
        return contentManagerService.getContentManager(id);
    }

    @PostMapping
    public ContentManagerReadDTO createContentManager(@RequestBody ContentManagerCreateDTO createDTO) {
        return contentManagerService.createContentManager(createDTO);
    }

    @PutMapping("/{id}")
    public ContentManagerReadDTO updateContentManager(@PathVariable UUID id,
                                                      @RequestBody ContentManagerPutDTO put) {
        return contentManagerService.updateContentManager(id, put);
    }

    @PatchMapping("/{id}")
    public ContentManagerReadDTO patchContentManager(@PathVariable UUID id,
                                                     @RequestBody ContentManagerPatchDTO patch) {
        return contentManagerService.patchContentManager(id, patch);
    }

    @DeleteMapping("/{id}")
    public void deleteContentManager(@PathVariable UUID id) {
        contentManagerService.deleteContentManager(id);
    }
}
