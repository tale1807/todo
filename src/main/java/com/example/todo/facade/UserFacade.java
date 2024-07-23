package com.example.todo.facade;

import com.example.todo.dao.exception.TaskCreationException;
import com.example.todo.dao.exception.UserNotFoundException;
import com.example.todo.dao.model.Task;
import com.example.todo.dao.service.TaskRepoService;
import com.example.todo.dao.service.UserRepoService;
import com.example.todo.dto.TaskRequest;
import com.example.todo.dto.TaskResponse;
import com.example.todo.mapper.UserRequestMapper;
import com.example.todo.mapper.UserResponseMapper;
import com.example.todo.dao.model.User;
import com.example.todo.dao.repository.UserRepository;
import com.example.todo.dto.UserRequest;
import com.example.todo.dto.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class UserFacade {

    private final UserRepoService userRepoService;

    public UserResponse createUser(UserRequest userRequest) {
            User user = userRepoService.toUser(userRequest);
            return userRepoService.toUsers(userRepoService.createUser(user));
    }

    public UserResponse getUserById(UUID id) {
        User user = userRepoService.getUserById(id);
        return userRepoService.toUsers(user);
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepoService.getAllUsers();
        return userRepoService.toUsers(users);

    }

    public UserResponse updateUser(UUID id, UserRequest userRequest) {
        User existingUser = userRepoService.getUserById(id);
        userRepoService.updateUser(userRequest, existingUser);
        return userRepoService.toUsers(userRepoService.createUser(existingUser));
    }

    public void deleteUser(UUID id) {
        userRepoService.delete(id);
    }

}
