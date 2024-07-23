package com.example.todo.dao.service;

import com.example.todo.dao.exception.TaskCreationException;
import com.example.todo.dao.exception.TaskNotFoundException;
import com.example.todo.dao.exception.UserCreationException;
import com.example.todo.dao.exception.UserNotFoundException;
import com.example.todo.dao.model.Task;
import com.example.todo.dao.model.User;
import com.example.todo.dao.repository.UserRepository;
import com.example.todo.dto.TaskRequest;
import com.example.todo.dto.TaskResponse;
import com.example.todo.dto.UserRequest;
import com.example.todo.dto.UserResponse;
import com.example.todo.mapper.UserRequestMapper;
import com.example.todo.mapper.UserResponseMapper;
import lombok.AllArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class UserRepoService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserCreationException(format("Ошибка при создании пользователя: %s", e.getMessage()));
        }
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(format("Не найден пользователь по заданному id - %s", id)));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    public void delete(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException((format("Не найден пользователь по заданному id - %s", id)));
        }
        userRepository.deleteById(id);
    }

}
