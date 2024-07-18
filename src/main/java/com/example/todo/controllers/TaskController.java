package com.example.todo.controllers;

import com.example.todo.models.Task;
import com.example.todo.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task newTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") UUID id, @Valid @RequestBody Task taskDetails) {
        Optional<Task> optionalTask = Optional.ofNullable(taskService.getTaskById(id));
        if (optionalTask.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Task task = optionalTask.get();
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setUpdatedAt(LocalDateTime.now());
        task.setStatus(taskDetails.getStatus());
        Task updatedTask = taskService.createTask(task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable UUID id) {
        Optional<Task> optionalTask = Optional.ofNullable(taskService.getTaskById(id));
        if (optionalTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        taskService.deleteTask(optionalTask.get().getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Task> getAllTasks() {
            return taskService.getAllTasks();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable UUID id, @RequestBody Boolean status) {
        Optional<Task> optionalTask = Optional.ofNullable(taskService.getTaskById(id));
        if (optionalTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Task task = optionalTask.get();
        task.setStatus(status);
        task.setUpdatedAt(LocalDateTime.now());
        Task updatedTask = taskService.createTask(task);
        return ResponseEntity.ok(updatedTask);
    }


}
