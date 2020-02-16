package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.Actor;
import com.coursesolvve.webproject.domain.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(statements = "delete from role", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("test")
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Test
    public void testSave() {
        Actor a = new Actor();
        actorRepository.save(a);
        Role r = new Role();
        r.setActor(a);
        r = roleRepository.save(r);
        assertNotNull(r.getId());
        assertTrue(roleRepository.findById(r.getId()).isPresent());
    }

    @Test
    public void testCreatedAtIsSet() {
        Role role = createRole();

        Instant createdAtBeforeReload = role.getCreatedAt();
        Assert.assertNotNull(createdAtBeforeReload);
        role = roleRepository.findById(role.getId()).get();

        Instant createdAtAfterReload = role.getCreatedAt();
        Assert.assertNotNull(createdAtAfterReload);
        Assert.assertEquals(createdAtBeforeReload, createdAtAfterReload);
    }

    @Test
    public void testUpdatedAtIsSet() {
        Role role = createRole();

        Instant createdAt = role.getCreatedAt();
        Instant updatedAtBeforeReload = role.getUpdatedAt();
        Assert.assertNotNull(updatedAtBeforeReload);
        Assert.assertEquals(createdAt, updatedAtBeforeReload);

        role.setInfo("update info test");
        role = roleRepository.save(role);
        role = roleRepository.findById(role.getId()).get();
        Instant updatedAtAfterReload = role.getUpdatedAt();
        Assert.assertTrue(updatedAtAfterReload.isAfter(updatedAtBeforeReload));
    }

    private Role createRole() {
        Actor actor = new Actor();
        actorRepository.save(actor);
        Role role = new Role();
        role.setActor(actor);
        role.setName("Role_test1");
        role.setInfo("This information is only for test");
        role.setRatingFull(2.0);
        role = roleRepository.save(role);
        return role;
    }
}
