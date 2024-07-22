package com.example.todo.controller;

import com.example.todo.dao.service.UserService;
import com.example.todo.dto.TaskRequest;
import com.example.todo.dto.TaskResponse;
import com.example.todo.dto.UserRequest;
import com.example.todo.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getTaskById(@PathVariable("id") UUID id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateTask(@PathVariable("id") UUID id, @Valid @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

}
