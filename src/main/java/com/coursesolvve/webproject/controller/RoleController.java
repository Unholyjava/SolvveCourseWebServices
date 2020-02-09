package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.dto.role.*;
import com.coursesolvve.webproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/{id}")
    public RoleReadExtendedDTO getRole(@PathVariable UUID id) {
        return roleService.getRole(id);
    }

    @PostMapping
    public RoleReadDTO createRole(@RequestBody RoleCreateDTO createDTO) {
        return roleService.createRole(createDTO);
    }

    @PatchMapping("/{id}")
    public RoleReadDTO patchRole(@PathVariable UUID id, @RequestBody RolePatchDTO patch) {
        return roleService.patchRole(id, patch);
    }

    @PutMapping("/{id}")
    public RoleReadDTO updateRole(@PathVariable UUID id, @RequestBody RolePutDTO put) {
        return roleService.updateRole(id, put);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable UUID id) {
        roleService.deleteRole(id);
    }
}
