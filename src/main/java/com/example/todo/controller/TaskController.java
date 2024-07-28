package com.example.todo.controller;

import com.example.todo.dto.TaskRequest;
import com.example.todo.dto.TaskResponse;
import com.example.todo.facade.TaskFacade;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
@Schema(name = "Контроллер для работы с задачами")
public class TaskController {

    private final TaskFacade taskFacade;


    @Schema(name = "Метод для создания задачи")
    @PostMapping
    public TaskResponse createTask(@Valid @RequestBody TaskRequest taskRequest) {
        return taskFacade.createTask(taskRequest);
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable("id") UUID id, @Valid @RequestBody TaskRequest taskRequest) {
        return taskFacade.updateTask(id, taskRequest);
    }

    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskFacade.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable("id") UUID id) {
        return taskFacade.getTaskById(id);
    }

    @PatchMapping("/{id}/status")
    public TaskResponse updateTaskStatus(@PathVariable UUID id, @RequestBody Boolean status) {
        return taskFacade.updateTaskStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable UUID id) {
        taskFacade.deleteTask(id);
    }
}

