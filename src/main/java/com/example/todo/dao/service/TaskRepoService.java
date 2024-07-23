package com.example.todo.dao.service;

import com.example.todo.dao.exception.TaskCreationException;
import com.example.todo.dao.exception.TaskNotFoundException;
import com.example.todo.dao.model.Task;
import com.example.todo.dao.repository.TaskRepository;
import com.example.todo.dto.TaskRequest;
import com.example.todo.dto.TaskResponse;
import com.example.todo.mapper.TaskRequestMapper;
import com.example.todo.mapper.TaskResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class TaskRepoService {

    private final TaskRepository taskRepository;
    private final TaskRequestMapper taskRequestMapper;
    private final TaskResponseMapper taskResponseMapper;

    public Task createTask(Task task) {
        try{
            return taskRepository.save(task);
        } catch (Exception e){
            throw new TaskCreationException(format("Error creating task: %s", e.getMessage()));
        }
    }

    public Task getTaskById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(
                                format("Не найдена задача по заданному id - %s", id))
                );
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();

    }

    public void delete(UUID id) {
        if(!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(format("Не найдена задача по заданному id - %s", id));
        }
        taskRepository.deleteById(id);
    }

    public TaskResponse toTasks(Task task) {
        return taskResponseMapper.toTasks(task);
    }

    public List<TaskResponse> toTasks(List<Task> tasks) {
        return taskResponseMapper.toTasks(tasks);
    }

    public Task toTask(TaskRequest taskRequest) {
        return taskRequestMapper.toTask(taskRequest);
    }

    public void updateTask(TaskRequest taskRequest, Task task) {
        taskRequestMapper.toTask(taskRequest, task);
    }

    public void updateTaskStatus(Boolean status, Task task) {
        taskRequestMapper.updateTaskStatusFromRequest(status, task);
    }
}


