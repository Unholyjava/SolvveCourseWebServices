package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.Like;
import com.coursesolvve.webproject.domain.LikedObjectType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(statements = "delete from client_like", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("test")
public class LikeRepositoryTest {

    @Autowired
    private LikeRepository likeRepository;

    @Test
    public void testSave() {
        Like l = new Like();
        l = likeRepository.save(l);
        assertNotNull(l.getId());
        assertTrue(likeRepository.findById(l.getId()).isPresent());
    }

    @Test
    public void testCreatedAtIsSet() {
        Like like = createLike();

        Instant createdAtBeforeReload = like.getCreatedAt();
        Assert.assertNotNull(createdAtBeforeReload);
        like = likeRepository.findById(like.getId()).get();

        Instant createdAtAfterReload = like.getCreatedAt();
        Assert.assertNotNull(createdAtAfterReload);
        Assert.assertEquals(createdAtBeforeReload, createdAtAfterReload);
    }

    @Test
    public void testUpdatedAtIsSet() {
        Like like = createLike();

        Instant createdAt = like.getCreatedAt();
        Instant updatedAtBeforeReload = like.getUpdatedAt();
        Assert.assertNotNull(updatedAtBeforeReload);
        Assert.assertEquals(createdAt, updatedAtBeforeReload);

        like.setLike(Boolean.FALSE);
        like = likeRepository.save(like);
        like = likeRepository.findById(like.getId()).get();
        Instant updatedAtAfterReload = like.getUpdatedAt();
        Assert.assertTrue(updatedAtAfterReload.isAfter(updatedAtBeforeReload));
    }

    private Like createLike() {
        Like like = new Like();
        like.setLike(Boolean.TRUE);
        like.setLikedObjectId(UUID.randomUUID());
        like.setLikeType(LikedObjectType.NEWS);
        like = likeRepository.save(like);
        return like;
    }
}
