package com.example.todo.facade;

import com.example.todo.dao.model.User;
import com.example.todo.dao.service.UserRepoService;
import com.example.todo.dto.UserRequest;
import com.example.todo.dto.UserResponse;
import com.example.todo.mapper.UserRequestMapper;
import com.example.todo.mapper.UserResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserFacade {

    private final UserRepoService userRepoService;
    private final UserResponseMapper userResponseMapper;
    private final UserRequestMapper userRequestMapper;

    public UserResponse createUser(UserRequest userRequest) {
            User user = userRequestMapper.toUser(userRequest);
            return userResponseMapper.toUsers(userRepoService.createUser(user));
    }

    public UserResponse getUserById(UUID id) {
        User user = userRepoService.getUserById(id);
        return userResponseMapper.toUsers(user);
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepoService.getAllUsers();
        return userResponseMapper.toUsers(users);

    }

    public UserResponse updateUser(UUID id, UserRequest userRequest) {
        User existingUser = userRepoService.getUserById(id);
        userRequestMapper.toUser(userRequest, existingUser);
        return userResponseMapper.toUsers(userRepoService.createUser(existingUser));
    }

    public void deleteUser(UUID id) {
        userRepoService.delete(id);
    }

}
