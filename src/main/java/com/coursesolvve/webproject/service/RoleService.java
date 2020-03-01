package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Role;
import com.coursesolvve.webproject.dto.role.*;
import com.coursesolvve.webproject.repository.RepositoryHelper;
import com.coursesolvve.webproject.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TranslationService translationService;

    @Autowired
    private RepositoryHelper repositoryHelper;

    public RoleReadExtendedDTO getRole(UUID id) {
        Role role = repositoryHelper.getEntityRequired(Role.class, id);
        return translationService.toReadExtended(role);
    }

    public RoleReadDTO createRole(RoleCreateDTO create) {
        Role role = new Role();
        role.setName(create.getName());
        role.setInfo(create.getInfo());
        role.setRatingFull(create.getRatingFull());
        role = roleRepository.save(role);
        return translationService.toRead(role);
    }

    public RoleReadDTO patchRole(UUID id, RolePatchDTO patch) {
        Role role = repositoryHelper.getEntityRequired(Role.class, id);

        if (patch.getName() != null) {
            role.setName(patch.getName());
        }
        if (patch.getInfo() != null) {
            role.setInfo(patch.getInfo());
        }
        if (patch.getRatingFull() != null) {
            role.setRatingFull(patch.getRatingFull());
        }

        role = roleRepository.save(role);
        return translationService.toRead(role);
    }

    public RoleReadDTO updateRole(UUID id, RolePutDTO put) {
        Role role = repositoryHelper.getEntityRequired(Role.class, id);

        role.setName(put.getName());
        role.setInfo(put.getInfo());
        role.setRatingFull(put.getRatingFull());
        role = roleRepository.save(role);
        return translationService.toRead(role);
    }

    public void deleteRole(UUID id) {
        roleRepository.delete(repositoryHelper.getEntityRequired(Role.class, id));
    }
}
