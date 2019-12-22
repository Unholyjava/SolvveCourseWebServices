package com.coursesolvve.webproject.service;

import com.coursesolvve.webproject.domain.User;
import com.coursesolvve.webproject.dto.UserReadDTO;
import com.coursesolvve.webproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserReadDTO getUser(UUID id) {
        User user = userRepository.findById(id).get();
        return toRead(user);
    }

    private UserReadDTO toRead(User user) {
        UserReadDTO userReadDTO = new UserReadDTO();
        userReadDTO.setId(user.getId());
        userReadDTO.setNickName(user.getNickName());
        userReadDTO.setLogin(user.getLogin());
        userReadDTO.setPassword(user.getPassword());
        return userReadDTO;
    }
}
