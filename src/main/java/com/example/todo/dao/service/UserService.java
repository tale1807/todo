package com.example.todo.dao.service;

import com.example.todo.dao.exception.TaskNotFoundException;
import com.example.todo.dao.exception.UserNotFoundException;
import com.example.todo.dao.mapper.UserRequestMapper;
import com.example.todo.dao.mapper.UserResponseMapper;
import com.example.todo.dao.model.User;
import com.example.todo.dao.repository.UserRepository;
import com.example.todo.dto.UserRequest;
import com.example.todo.dto.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UserService {

    private final UserRepository userRepository;

    public UserResponse createUser(UserRequest userRequest) {
        User task = UserRequestMapper.INSTANCE.toUser(userRequest);
        return UserResponseMapper.INSTANCE.toUsers(userRepository.save(task));
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserResponseMapper.INSTANCE.toUsers(users);
    }


    public UserResponse getUserById(UUID id) {
        return UserResponseMapper.INSTANCE.toUsers(userRepository
                .findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                format("Не найден пользователь по заданному id - %s", id))
                ));
    }

    public UserResponse updateUser(UUID id, UserRequest userRequest) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                format("Не найден пользователь по заданному id - %s", id)));

        UserRequestMapper.INSTANCE.toUser(userRequest, existingUser);

        return UserResponseMapper.INSTANCE.toUsers(userRepository.save(existingUser));
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

}
