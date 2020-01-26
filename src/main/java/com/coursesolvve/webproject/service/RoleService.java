package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Role;
import com.coursesolvve.webproject.dto.role.*;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
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

    public RoleReadExtendedDTO getRole(UUID id) {
        Role role = getRoleRequired(id);
        return translationService.toReadExtended(role);
    }

    /*public RoleReadDTO getRole(UUID id) {
        Role role = getRoleRequired(id);
        return toRead(role);
    }*/

    public RoleReadDTO createRole(RoleCreateDTO create) {
        Role role = new Role();
        role.setName(create.getName());
        role.setInfo(create.getInfo());
        role.setRatingFull(create.getRatingFull());
        role = roleRepository.save(role);
        return toRead(role);
    }

    public RoleReadDTO patchRole(UUID id, RolePatchDTO patch) {
        Role role = getRoleRequired(id);

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
        return toRead(role);
    }

    public RoleReadDTO putRole(UUID id, RolePutDTO put) {
        Role role = getRoleRequired(id);

        role.setName(put.getName());
        role.setInfo(put.getInfo());
        role.setRatingFull(put.getRatingFull());
        role = roleRepository.save(role);
        return toRead(role);
    }

    public void deleteRole(UUID id) {
        roleRepository.delete(getRoleRequired(id));
    }

    private Role getRoleRequired(UUID id) {
        return roleRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Role.class, id);
        });
    }

    private RoleReadDTO toRead(Role role) {
        RoleReadDTO roleReadDTO = new RoleReadDTO();
        roleReadDTO.setId(role.getId());
        roleReadDTO.setName(role.getName());
        roleReadDTO.setInfo(role.getInfo());
        roleReadDTO.setRatingFull(role.getRatingFull());
        return roleReadDTO;
    }

    /*private ActorReadDTO toRead(Actor actor) {
        ActorReadDTO actorReadDTO = new ActorReadDTO();
        actorReadDTO.setId(actor.getId());
        actorReadDTO.setName(actor.getName());
        actorReadDTO.setPatronymic(actor.getPatronymic());
        actorReadDTO.setSurname(actor.getSurname());
        actorReadDTO.setInfo(actor.getInfo());
        actorReadDTO.setRatingFull(actor.getRatingFull());
        return actorReadDTO;
    }*/
}
