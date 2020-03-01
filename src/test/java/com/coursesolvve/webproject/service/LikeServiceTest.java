package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Like;
import com.coursesolvve.webproject.domain.LikedObjectType;
import com.coursesolvve.webproject.dto.like.LikeCreateDTO;
import com.coursesolvve.webproject.dto.like.LikePatchDTO;
import com.coursesolvve.webproject.dto.like.LikePutDTO;
import com.coursesolvve.webproject.dto.like.LikeReadDTO;
import com.coursesolvve.webproject.exception.EntityNotFoundException;
import com.coursesolvve.webproject.repository.LikeRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(statements = "delete from client_like", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class LikeServiceTest {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private LikeService likeService;

    @Test
    public void testGetLike() {
        Like like = createLike();

        LikeReadDTO readDTO = likeService.getLike(like.getId());
        Assertions.assertThat(readDTO).isEqualToComparingFieldByField(like);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetLikeWrongId() {
        likeService.getLike(UUID.randomUUID());
    }

    @Test
    public void testCreateLike() {
        LikeCreateDTO create = new LikeCreateDTO();
        create.setLike(Boolean.TRUE);
        create.setLikedObjectId(UUID.randomUUID());
        create.setLikeType(LikedObjectType.REVIEW);

        LikeReadDTO read = likeService.createLike(create);

        Assertions.assertThat(create).isEqualToComparingFieldByField(read);
        Assert.assertNotNull(read.getId());

        Like like = likeRepository.findById(read.getId()).get();
        Assertions.assertThat(read).isEqualToComparingFieldByField(like);
    }

    @Test
    public void testPatchLike() {
        Like like = createLike();
        LikePatchDTO patch = new LikePatchDTO();
        patch.setLike(Boolean.FALSE);
        patch.setLikedObjectId(UUID.randomUUID());
        patch.setLikeType(LikedObjectType.REVIEW);

        LikeReadDTO read = likeService.patchLike(like.getId(), patch);

        Assertions.assertThat(patch).isEqualToComparingFieldByField(read);

        like = likeRepository.findById(read.getId()).get();
        Assertions.assertThat(like).isEqualToComparingFieldByField(read);
    }

    @Test
    public void testPatchLikeEmptyPatch() {
        Like like = createLike();
        LikePatchDTO patch = new LikePatchDTO();
        LikeReadDTO read = likeService.patchLike(like.getId(), patch);
        Assertions.assertThat(read).hasNoNullFieldsOrProperties();

        Like likeAfterUpdate = likeRepository.findById(read.getId()).get();
        Assertions.assertThat(likeAfterUpdate).hasNoNullFieldsOrProperties();

        Assertions.assertThat(like).isEqualToComparingFieldByField(likeAfterUpdate);
    }

    @Test
    public void testUpdateLike() {
        Like like = createLike();

        LikePutDTO put = new LikePutDTO();
        put.setLike(Boolean.FALSE);
        put.setLikedObjectId(UUID.randomUUID());
        put.setLikeType(LikedObjectType.REVIEW);

        LikeReadDTO read = likeService.updateLike(like.getId(), put);

        Assertions.assertThat(put).isEqualToComparingFieldByField(read);

        like = likeRepository.findById(read.getId()).get();
        Assertions.assertThat(like).isEqualToComparingFieldByField(read);
    }

    @Test
    public void testDeleteLike() {
        Like like = createLike();

        likeService.deleteLike(like.getId());
        Assert.assertFalse(likeRepository.existsById(like.getId()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteLikeNotFound() {
        likeService.deleteLike(UUID.randomUUID());
    }

    private Like createLike() {
        Like like = new Like();
        like.setLike(Boolean.TRUE);
        like.setLikedObjectId(UUID.randomUUID());
        like.setLikeType(LikedObjectType.REVIEW);
        like = likeRepository.save(like);
        return like;
    }
}
