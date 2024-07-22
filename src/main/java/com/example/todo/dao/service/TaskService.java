package com.example.todo.dao.service;

import com.example.todo.dao.exception.TaskCreationException;
import com.example.todo.dao.exception.TaskNotFoundException;
import com.example.todo.dao.mapper.TaskRequestMapper;
import com.example.todo.dao.mapper.TaskResponseMapper;
import com.example.todo.dto.TaskRequest;
import com.example.todo.dto.TaskResponse;
import com.example.todo.dao.model.Task;
import com.example.todo.dao.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskResponse createTask(TaskRequest taskRequest) {
        try{
            Task task = TaskRequestMapper.INSTANCE.toTask(taskRequest);
            return TaskResponseMapper.INSTANCE.toTasks(taskRepository.save(task));
        } catch (Exception e){
            throw new TaskCreationException("Error creating task", e);
        }
    }

    public TaskResponse getTaskById(UUID id) {
        return TaskResponseMapper.INSTANCE.toTasks(taskRepository
                .findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(
                                format("Не найдена задача по заданному id - %s", id))
                ));
    }

    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return TaskResponseMapper.INSTANCE.toTasks(tasks);

    }

    public TaskResponse updateTask(UUID id, TaskRequest taskRequest) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(format("Не найдена задача по заданному id - %s", id)));

        TaskRequestMapper.INSTANCE.toTask(taskRequest, existingTask);

        return TaskResponseMapper.INSTANCE.toTasks(taskRepository.save(existingTask));
    }

    public TaskResponse updateTaskStatus(UUID id, Boolean status) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(format("Не найдена задача по заданному id - %s", id)));

        TaskRequestMapper.INSTANCE.updateTaskStatusFromRequest(status, existingTask);

        return TaskResponseMapper.INSTANCE.toTasks(taskRepository.save(existingTask));

    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
}


