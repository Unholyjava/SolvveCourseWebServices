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

    /*@Test
    public void testGetRole() throws Exception {
        RoleReadDTO read = createRoleRead();

        Mockito.when(roleService.getRole(read.getId())).thenReturn(read);

        String resultJson = mvc.perform(get("/api/v1/roles/{id}", read.getId()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(resultJson);
        RoleReadDTO actualRole = objectMapper.readValue(resultJson, RoleReadDTO.class);
        Assertions.assertThat(actualRole).isEqualToComparingFieldByField(read);

        Mockito.verify(roleService).getRole(read.getId());
    }*/
   /* @Test
    public void testGetRoleWrongId() throws Exception {
        UUID wrongId = UUID.randomUUID();

        EntityNotFoundException exception = new EntityNotFoundException(Role.class, wrongId);
        Mockito.when(roleService.getRole(wrongId)).thenThrow(exception);

        String resultJson = mvc.perform(get("/api/v1/roles/{id}", wrongId))
                .andExpect(status().isNotFound())
                .andReturn().getResponse().getContentAsString();
    }*/

    @Test
    public void testGetRoleWrongUuidFormat() throws Exception {
        UUID id = UUID.randomUUID();
        String wrongUuidId = id.toString() + id.toString();
        String resultJson = mvc.perform(get("/api/v1/roles/{id}", wrongUuidId))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();
    }

    /*@Test
    public void testCreateRole() throws Exception {
        RoleCreateDTO create = new RoleCreateDTO();
        create.setName("Role_test2_create");
        create.setInfo("This information is only for test2_create");
        create.setRatingFull(3);

        RoleReadDTO read = createRoleRead();

        Mockito.when(roleService.createRole(create)).thenReturn(read);
        String resultJson = mvc.perform(post("/api/v1/roles")
                .content(objectMapper.writeValueAsString(create))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        RoleReadDTO actualRole = objectMapper.readValue(resultJson, RoleReadDTO.class);
        Assertions.assertThat(actualRole).isEqualToComparingFieldByField(read);
    }

    @Test
    public void testPatchRole() throws Exception {
        RolePatchDTO patchDTO = new RolePatchDTO();
        patchDTO.setName("Role_test2_create");
        patchDTO.setInfo("This information is only for test2_create");
        patchDTO.setRatingFull(3.0);

        RoleReadExtendedDTO read = createRoleRead();

        Mockito.when(roleService.patchRole(read.getId(), patchDTO)).thenReturn(read);

        String resultJson = mvc.perform(patch("/api/v1/roles/{id}", read.getId().toString())
                .content(objectMapper.writeValueAsString(patchDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        RoleReadDTO actualRole = objectMapper.readValue(resultJson, RoleReadDTO.class);
        Assert.assertEquals(read, actualRole);
    }

    @Test
    public void testDeleteRole() throws Exception {
        UUID id = UUID.randomUUID();

        mvc.perform(delete("/api/v1/roles/{id}", id.toString())).andExpect(status().isOk());

        Mockito.verify(roleService).deleteRole(id);
    }*/

    private RoleReadDTO createRoleRead() {
        Actor actor = new Actor();
        actor.setName("Actor_test1");
        actor.setPatronymic("Actor_Patronymic");
        actor.setSurname("Actor_Surname");
        actor.setInfo("This information is only for test");
        actor.setRatingFull(2);

        RoleReadDTO read = new RoleReadDTO();
        read.setId(UUID.randomUUID());
        read.setName("Role_test1");
        read.setInfo("This information is only for test");
        read.setRatingFull(3);
        //read.setActor(translationService.toRead(actor));
        return read;
    }
}
