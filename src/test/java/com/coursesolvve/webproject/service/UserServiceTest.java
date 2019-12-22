package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.User;
import com.coursesolvve.webproject.dto.UserReadDTO;
import com.coursesolvve.webproject.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(statements = "delete from user", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testGetUser() {
        User user = new User();
        user.setNickName("User_test1");
        user.setLogin("UserLogin_test1");
        user.setPassword("UserPassword_test1");
        user = userRepository.save(user);

        UserReadDTO readDTO = userService.getUser(user.getId());
        Assertions.assertThat(readDTO).isEqualToComparingFieldByField(user);
    }
}
