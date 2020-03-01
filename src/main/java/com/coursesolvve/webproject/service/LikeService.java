package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Like;
import com.coursesolvve.webproject.dto.like.LikeCreateDTO;
import com.coursesolvve.webproject.dto.like.LikePatchDTO;
import com.coursesolvve.webproject.dto.like.LikePutDTO;
import com.coursesolvve.webproject.dto.like.LikeReadDTO;
import com.coursesolvve.webproject.repository.LikeRepository;
import com.coursesolvve.webproject.repository.RepositoryHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private RepositoryHelper repositoryHelper;

    public LikeReadDTO getLike(UUID id) {
        Like like = repositoryHelper.getEntityRequired(Like.class, id);
        return toRead(like);
    }

    public LikeReadDTO createLike(LikeCreateDTO create) {
        Like like = new Like();
        like.setLike(create.getLike());
        like.setLikedObjectId(create.getLikedObjectId());
        like.setLikeType(create.getLikeType());
        like = likeRepository.save(like);
        return toRead(like);
    }

    public LikeReadDTO patchLike(UUID id, LikePatchDTO patch) {
        Like like = repositoryHelper.getEntityRequired(Like.class, id);

        if (patch.getLike() != null) {
            like.setLike(patch.getLike());
        }
        if (patch.getLikedObjectId() != null) {
            like.setLikedObjectId(patch.getLikedObjectId());
        }
        if (patch.getLikeType() != null) {
            like.setLikeType(patch.getLikeType());
        }

        like = likeRepository.save(like);
        return toRead(like);
    }

    public LikeReadDTO updateLike(UUID id, LikePutDTO put) {
        Like like = repositoryHelper.getEntityRequired(Like.class, id);

        like.setLike(put.getLike());
        like.setLikedObjectId(put.getLikedObjectId());
        like.setLikeType(put.getLikeType());

        like = likeRepository.save(like);
        return toRead(like);
    }

    public void deleteLike(UUID id) {
        likeRepository.delete(repositoryHelper.getEntityRequired(Like.class, id));
    }

    private LikeReadDTO toRead(Like like) {
        LikeReadDTO likeReadDTO = new LikeReadDTO();
        likeReadDTO.setId(like.getId());
        likeReadDTO.setCreatedAt(like.getCreatedAt());
        likeReadDTO.setUpdatedAt(like.getUpdatedAt());
        likeReadDTO.setLike(like.getLike());
        likeReadDTO.setLikedObjectId(like.getLikedObjectId());
        likeReadDTO.setLikeType(like.getLikeType());
        return likeReadDTO;
    }
}
