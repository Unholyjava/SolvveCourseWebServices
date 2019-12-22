package com.coursesolvve.webproject.controller;

import com.coursesolvve.webproject.dto.UserReadDTO;
import com.coursesolvve.webproject.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void testGetUser() throws Exception {
        UserReadDTO user = new UserReadDTO();
        user.setId(UUID.randomUUID());
        user.setNickName("User_test1");
        user.setLogin("UserLogin_test1");
        user.setPassword("UserPassword_test1");

        Mockito.when(userService.getUser(user.getId())).thenReturn(user);

        String resultJson = mvc.perform(get("/api/v1/users/{id}", user.getId()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(resultJson);
        UserReadDTO actualUser = objectMapper.readValue(resultJson, UserReadDTO.class);
        Assertions.assertThat(actualUser).isEqualToComparingFieldByField(user);

        Mockito.verify(userService).getUser(user.getId());
    }
}
