package com.example.todo.controller;

import com.example.todo.dto.TaskRequest;
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

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    TaskFacade taskFacade;

    @Autowired
    UserFacade userFacade;

    UUID user;
    UUID task;

    @BeforeEach
    public void setUp() {
        UserRequest userRequest = new UserRequest("Test User", "test@example.com", "password");
        user = userFacade.createUser(userRequest).getId();
        TaskRequest taskRequest = new TaskRequest(user, "Test Task", "Test Description", true);
        task = taskFacade.createTask(taskRequest).getId();
    }

    @Test
    void shouldCreateTask() throws Exception {
        TaskRequest taskRequest = new TaskRequest(user, "New Test Task",
                "New Test Description", true);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"user\":\"" + taskRequest.getUser() + "\",\"title\":\""
                                 + taskRequest.getTitle() + "\",\"description\":\""
                                 + taskRequest.getDescription() + "\",\"status\":\"" + taskRequest.getStatus() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(taskRequest.getTitle()));
    }

    @Test
    void shouldGetAllTasks() throws Exception {
        mockMvc.perform(get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateTask() throws Exception {
        TaskRequest taskRequest = new TaskRequest(user, "Updated Task", "Updated Description", false);

        mockMvc.perform(put("/tasks/" + task)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"user\":\"" + taskRequest.getUser() + "\",\"title\":\""
                                 + taskRequest.getTitle() + "\",\"description\":\""
                                 + taskRequest.getDescription() + "\",\"status\":\"" + taskRequest.getStatus() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(taskRequest.getTitle()));
    }

    @Test
    void shouldGetTaskById() throws Exception {
        mockMvc.perform(get("/tasks/" + task)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(task.toString()));
    }

    @Test
    void shouldUpdateTaskStatus() throws Exception {
        mockMvc.perform(patch("/tasks/" + task + "/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("false"));
    }

    @Test
    void shouldDeleteTask() throws Exception {
        mockMvc.perform(delete("/tasks/" + task)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
