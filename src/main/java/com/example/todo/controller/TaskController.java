package com.example.todo.controller;

import com.example.todo.dao.mapper.TaskResponseMapper;
import com.example.todo.dto.TaskRequest;
import com.example.todo.dto.TaskResponse;
import com.example.todo.dao.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public TaskResponse createTask(@Valid @RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable("id") UUID id, @Valid @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(id, taskRequest);
    }

    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable("id") UUID id) {
        return taskService.getTaskById(id);
    }

    @PatchMapping("/{id}/status")
    public TaskResponse updateTaskStatus(@PathVariable UUID id, @RequestBody Boolean status) {
        return taskService.updateTaskStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
    }
}

