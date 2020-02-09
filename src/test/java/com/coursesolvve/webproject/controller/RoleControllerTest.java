package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.domain.Actor;
import com.coursesolvve.webproject.dto.role.RoleReadDTO;
import com.coursesolvve.webproject.service.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RoleController.class)
public class RoleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RoleService roleService;

    @Test
    public void testGetRoleWrongUuidFormat() throws Exception {
        UUID id = UUID.randomUUID();
        String wrongUuidId = id.toString() + id.toString();
        String resultJson = mvc.perform(get("/api/v1/roles/{id}", wrongUuidId))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();
    }

    private RoleReadDTO createRoleRead() {
        Actor actor = new Actor();
        actor.setName("Actor_test1");
        actor.setPatronymic("Actor_Patronymic");
        actor.setSurname("Actor_Surname");
        actor.setInfo("This information is only for test");
        actor.setRatingFull(2.0);

        RoleReadDTO read = new RoleReadDTO();
        read.setId(UUID.randomUUID());
        read.setName("Role_test1");
        read.setInfo("This information is only for test");
        read.setRatingFull(3);
        return read;
    }
}
