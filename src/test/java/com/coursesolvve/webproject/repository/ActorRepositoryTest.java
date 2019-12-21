package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(statements = "delete from actor", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("test")
public class ActorRepositoryTest {

    @Autowired
    private ActorRepository actorRepository;

    @Test
    public void testSave() {
        Actor c = new Actor();
        c = actorRepository.save(c);
        assertNotNull(c.getId());
        assertTrue(actorRepository.findById(c.getId()).isPresent());
    }
}
