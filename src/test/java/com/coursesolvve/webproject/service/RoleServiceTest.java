package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Actor;
import com.coursesolvve.webproject.domain.Role;
import com.coursesolvve.webproject.dto.role.*;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.repository.ActorRepository;
import com.coursesolvve.webproject.repository.RoleRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(statements = {
        "delete from role",
        "delete from actor"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RoleServiceTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private RoleService roleService;

    @Test
    public void testGetRoleExtended() {
        Actor actor = createActor();
        Role role = createRole(actor);
        RoleReadExtendedDTO read = roleService.getRole(role.getId());
        Assertions.assertThat(read).isEqualToIgnoringGivenFields(role, "actor");
        Assertions.assertThat(read.getActor()).isEqualToIgnoringGivenFields(actor);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetRoleWrongId() {
        roleService.getRole(UUID.randomUUID());
    }

    @Test
    @Transactional
    public void testCreateRole() {
        RoleCreateDTO create = new RoleCreateDTO();
        create.setName("Role_test2_create");
        create.setInfo("This information is only for test2_create");
        create.setRatingFull(2);
        RoleReadDTO read = roleService.createRole(create);
        Assertions.assertThat(create).isEqualToComparingFieldByField(read);
        Assert.assertNotNull(read.getId());

        Role role = roleRepository.findById(read.getId()).get();
        Assertions.assertThat(read).isEqualToIgnoringGivenFields(role, "actorId");
    }

    @Test
    @Transactional
    public void testPatchRole() {
        Role role = createRole();

        RolePatchDTO patch = new RolePatchDTO();
        patch.setName("Role_test2_patch");
        patch.setInfo("This information is only for test2");
        patch.setRatingFull(2.0);
        RoleReadDTO read = roleService.patchRole(role.getId(), patch);

        Assertions.assertThat(patch).isEqualToComparingFieldByField(read);

        role = roleRepository.findById(read.getId()).get();
        Assertions.assertThat(role).isEqualToIgnoringGivenFields(read, "actor");
    }

    @Test
    @Transactional
    public void testPatchRoleEmptyPatch() {
        Role role = createRole();

        RolePatchDTO patch = new RolePatchDTO();
        RoleReadDTO read = roleService.patchRole(role.getId(), patch);
        Assertions.assertThat(read).hasNoNullFieldsOrPropertiesExcept("actorId");

        Role roleAfterUpdate = roleRepository.findById(read.getId()).get();
        Assertions.assertThat(roleAfterUpdate).hasNoNullFieldsOrPropertiesExcept("actor");

        Assertions.assertThat(role).isEqualToIgnoringGivenFields(roleAfterUpdate, "actor");
    }

    @Test
    @Transactional
    public void testUpdateRole() {
        Role role = createRole();

        RolePutDTO put = new RolePutDTO();
        put.setName("Role_test2_put");
        put.setInfo("This information is only for test2");
        put.setRatingFull(2.0);
        RoleReadDTO read = roleService.updateRole(role.getId(), put);

        Assertions.assertThat(put).isEqualToComparingFieldByField(read);

        role = roleRepository.findById(read.getId()).get();
        Assertions.assertThat(role).isEqualToIgnoringGivenFields(read, "actor");
    }

    @Test
    public void testDeleteRole() {
        Role role = createRole(createActor());

        roleService.deleteRole(role.getId());
        Assert.assertFalse(roleRepository.existsById(role.getId()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteRoleNotFound() {
        roleService.deleteRole(UUID.randomUUID());
    }

    private Role createRole(Actor actor) {
        Role role = new Role();
        role.setName("Role_test1");
        role.setInfo("This information is only for test");
        role.setRatingFull(2.0);
        role.setActor(actor);
        role = roleRepository.save(role);
        return role;
    }

    private Role createRole() {
        Role role = new Role();
        role.setName("Role_test1");
        role.setInfo("This information is only for test");
        role.setRatingFull(2.0);
        role = roleRepository.save(role);
        return role;
    }

    private Actor createActor() {
        Actor actor = new Actor();
        actor.setName("Actor_test1");
        actor.setPatronymic("Actor_Patronymic");
        actor.setSurname("Actor_Surname");
        actor.setInfo("This information is only for test");
        actor.setRatingFull(2.0);
        actor = actorRepository.save(actor);
        return actor;
    }
}
