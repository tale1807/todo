package com.example.todo.controller;

import com.example.todo.dao.model.Task;
import com.example.todo.dto.TaskRequest;
import com.example.todo.dto.TaskResponse;
import com.example.todo.dto.UserRequest;
import com.example.todo.facade.TaskFacade;
import com.example.todo.facade.UserFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskFacade taskFacade;

    @Autowired
    private UserFacade userFacade;

    private UUID user;

    @BeforeEach
    void setup() {
        UserRequest userRequest = new UserRequest("Test User", "test@example.com", "password");
        user = userFacade.createUser(userRequest).getId();

    }

    @Test
    void shouldCreateUser() throws Exception {
        UserRequest userRequest = new UserRequest("New User", "new@example.com", "newpassword");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"" + userRequest.getName() + "\",\"email\":\"" + userRequest.getEmail()
                                 + "\",\"password\":\"" + userRequest.getPassword() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(userRequest.getName()));
    }

    @Test
    void shouldGetAllUsers() throws Exception {
        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetUserById() throws Exception {
        mockMvc.perform(get("/users/" + user)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.toString()));
    }

    @Test
    void shouldUpdateUser() throws Exception {
        UserRequest userRequest = new UserRequest("Updated User", "updated@example.com",
                "updatedpassword");

        mockMvc.perform(put("/users/" + user)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"" + userRequest.getName() + "\",\"email\":\"" + userRequest.getEmail()
                                 + "\",\"password\":\"" + userRequest.getPassword() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(userRequest.getName()));
    }

    @Test
    void shouldDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/" + user)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}


