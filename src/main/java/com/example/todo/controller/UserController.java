package com.example.todo.controller;

import com.example.todo.dto.UserRequest;
import com.example.todo.dto.UserResponse;
import com.example.todo.facade.UserFacade;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {
        return userFacade.createUser(userRequest);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userFacade.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable("id") UUID id) {
        return userFacade.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable("id") UUID id, @Valid @RequestBody UserRequest userRequest) {
        return userFacade.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userFacade.deleteUser(id);
    }

}
