package com.example.todo.service;

import com.example.todo.dto.TaskRequest;
import com.example.todo.dto.TaskResponse;
import com.example.todo.exception.TaskNotFoundException;
import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    public TaskResponse createTask(TaskRequest taskRequest) {
        try {
            Task task = convertToEntity(taskRequest);
            Task newTask = taskRepository.save(task);
            return convertToResponse(newTask);
        } catch (TaskNotFoundException e){
            throw new TaskNotFoundException("Error creating task", e);
        }
    }

    public TaskResponse updateTask(UUID id, TaskRequest taskRequest) {
        try{
            Task existingTask = taskRepository.findById(id)
                    .orElseThrow(TaskNotFoundException::new);
            Task updatedTask = new Task();

            updatedTask.setId(existingTask.getId());
            updatedTask.setCreatedAt(existingTask.getCreatedAt());
            updatedTask.setUpdatedAt(LocalDateTime.now());
            updatedTask.setTitle(taskRequest.getTitle());
            updatedTask.setDescription(taskRequest.getDescription());
            updatedTask.setStatus(taskRequest.getStatus());

            Task savedTask = taskRepository.save(updatedTask);
            return convertToResponse(savedTask);
        } catch (TaskNotFoundException e){
            throw new TaskNotFoundException("Error updating task", e);
        }


    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }

    public TaskResponse getTaskById(UUID id) {
        try {
            return convertToResponse(taskRepository
                    .findById(id)
                    .orElseThrow(TaskNotFoundException::new));
        } catch (TaskNotFoundException e){
            throw new TaskNotFoundException("Error getting task by ID", e);
        }

    }

    public List<TaskResponse> getAllTasks() {
        try {
            List<Task> tasks = taskRepository.findAll();
            return tasks.stream()
                    .map(this::convertToResponse)
                    .collect(Collectors.toList());
        } catch (TaskNotFoundException e){
            throw new TaskNotFoundException("Error getting all tasks", e);
        }

    }

    public TaskResponse updateTaskStatus(UUID id, Boolean status) {
        try {
            Task existingTask = taskRepository.findById(id)
                    .orElseThrow(TaskNotFoundException::new);

            Task updatedTask = new Task();
            updatedTask.setId(existingTask.getId());
            updatedTask.setCreatedAt(existingTask.getCreatedAt());
            updatedTask.setUpdatedAt(LocalDateTime.now());
            updatedTask.setTitle(existingTask.getTitle());
            updatedTask.setDescription(existingTask.getDescription());
            updatedTask.setStatus(status);

            Task savedTask = taskRepository.save(updatedTask);
            return convertToResponse(savedTask);
        } catch (TaskNotFoundException e){
            throw new TaskNotFoundException("Error updating task status", e);
        }

    }

    private Task convertToEntity(TaskRequest taskRequest) {
        return modelMapper.map(taskRequest, Task.class);
    }

    private TaskResponse convertToResponse(Task task) {
        TaskResponse response = modelMapper.map(task, TaskResponse.class);
        response.setStatus(task.getStatus() ? "complete" : "incomplete");
        return response;
    }
}


