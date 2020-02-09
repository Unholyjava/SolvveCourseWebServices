package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.Actor;
import com.coursesolvve.webproject.domain.ContentManager;
import com.coursesolvve.webproject.domain.News;
import com.coursesolvve.webproject.domain.Role;
import com.coursesolvve.webproject.dto.actor.ActorCreateDTO;
import com.coursesolvve.webproject.dto.actor.ActorPatchDTO;
import com.coursesolvve.webproject.dto.actor.ActorPutDTO;
import com.coursesolvve.webproject.dto.actor.ActorReadDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerCreateDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerPatchDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerPutDTO;
import com.coursesolvve.webproject.dto.contentmanager.ContentManagerReadDTO;
import com.coursesolvve.webproject.dto.news.NewsReadDTO;
import com.coursesolvve.webproject.dto.news.NewsReadExtendedDTO;
import com.coursesolvve.webproject.dto.role.RoleReadExtendedDTO;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {

    public RoleReadExtendedDTO toReadExtended(Role role) {
        RoleReadExtendedDTO roleReadDTO = new RoleReadExtendedDTO();
        roleReadDTO.setId(role.getId());
        roleReadDTO.setName(role.getName());
        roleReadDTO.setInfo(role.getInfo());
        roleReadDTO.setRatingFull(role.getRatingFull());
        roleReadDTO.setActor(toRead(role.getActor()));
        return roleReadDTO;
    }

    public ActorReadDTO toRead(Actor actor) {
        ActorReadDTO actorReadDTO = new ActorReadDTO();
        actorReadDTO.setId(actor.getId());
        actorReadDTO.setName(actor.getName());
        actorReadDTO.setPatronymic(actor.getPatronymic());
        actorReadDTO.setSurname(actor.getSurname());
        actorReadDTO.setInfo(actor.getInfo());
        actorReadDTO.setRatingFull(actor.getRatingFull());
        return actorReadDTO;
    }

    public Actor toEntity(ActorCreateDTO create) {
        Actor actor = new Actor();
        actor.setName(create.getName());
        actor.setPatronymic(create.getPatronymic());
        actor.setSurname(create.getSurname());
        actor.setInfo(create.getInfo());
        actor.setRatingFull(create.getRatingFull());
        return actor;
    }

    public void patchEntity(ActorPatchDTO patch, Actor actor) {
        if (patch.getName() != null) {
            actor.setName(patch.getName());
        }
        if (patch.getPatronymic() != null) {
            actor.setPatronymic(patch.getPatronymic());
        }
        if (patch.getSurname() != null) {
            actor.setSurname(patch.getSurname());
        }
        if (patch.getInfo() != null) {
            actor.setInfo(patch.getInfo());
        }
        if (patch.getRatingFull() != null) {
            actor.setRatingFull(patch.getRatingFull());
        }
    }

    public void updateEntity(ActorPutDTO put, Actor actor) {
        actor.setName(put.getName());
        actor.setPatronymic(put.getPatronymic());
        actor.setSurname(put.getSurname());
        actor.setInfo(put.getInfo());
        actor.setRatingFull(put.getRatingFull());
    }

    public NewsReadExtendedDTO toReadExtended(News news) {
        NewsReadExtendedDTO newsReadDTO = new NewsReadExtendedDTO();
        newsReadDTO.setId(news.getId());
        newsReadDTO.setInfo(news.getInfo());
        newsReadDTO.setNewsMistake(news.getNewsMistake());
        newsReadDTO.setLikeRating(news.getLikeRating());
        newsReadDTO.setContentManager(toRead(news.getContentManager()));
        return newsReadDTO;
    }

    public NewsReadDTO toRead(News news) {
        NewsReadDTO newsReadDTO = new NewsReadDTO();
        newsReadDTO.setId(news.getId());
        newsReadDTO.setInfo(news.getInfo());
        newsReadDTO.setNewsMistake(news.getNewsMistake());
        newsReadDTO.setLikeRating(news.getLikeRating());
        return newsReadDTO;
    }

    public ContentManagerReadDTO toRead(ContentManager contentManager) {
        ContentManagerReadDTO contentManagerReadDTO = new ContentManagerReadDTO();
        contentManagerReadDTO.setId(contentManager.getId());
        contentManagerReadDTO.setNickName(contentManager.getNickName());
        contentManagerReadDTO.setLogin(contentManager.getLogin());
        contentManagerReadDTO.setMail(contentManager.getMail());
        contentManagerReadDTO.setName(contentManager.getName());
        contentManagerReadDTO.setPatronymic(contentManager.getPatronymic());
        contentManagerReadDTO.setSurname(contentManager.getSurname());
        return contentManagerReadDTO;
    }

    public ContentManager toEntity(ContentManagerCreateDTO create) {
        ContentManager contentManager = new ContentManager();
        contentManager.setNickName(create.getNickName());
        contentManager.setLogin(create.getLogin());
        contentManager.setMail(create.getMail());
        contentManager.setName(create.getName());
        contentManager.setPatronymic(create.getPatronymic());
        contentManager.setSurname(create.getSurname());
        return contentManager;
    }

    public void patchEntity(ContentManagerPatchDTO patch, ContentManager contentManager) {
        if (patch.getNickName() != null) {
            contentManager.setNickName(patch.getNickName());
        }
        if (patch.getLogin() != null) {
            contentManager.setLogin(patch.getLogin());
        }
        if (patch.getMail() != null) {
            contentManager.setMail(patch.getMail());
        }
        if (patch.getName() != null) {
            contentManager.setName(patch.getName());
        }
        if (patch.getPatronymic() != null) {
            contentManager.setPatronymic(patch.getPatronymic());
        }
        if (patch.getSurname() != null) {
            contentManager.setSurname(patch.getSurname());
        }
    }

    public void updateEntity(ContentManagerPutDTO put, ContentManager contentManager) {
        contentManager.setNickName(put.getNickName());
        contentManager.setLogin(put.getLogin());
        contentManager.setMail(put.getMail());
        contentManager.setName(put.getName());
        contentManager.setPatronymic(put.getPatronymic());
        contentManager.setSurname(put.getSurname());
    }
}
