package com.example.todo.facade;

import com.example.todo.dao.exception.TaskCreationException;
import com.example.todo.dao.exception.TaskNotFoundException;
import com.example.todo.dao.service.TaskRepoService;
import com.example.todo.mapper.TaskRequestMapper;
import com.example.todo.mapper.TaskResponseMapper;
import com.example.todo.dto.TaskRequest;
import com.example.todo.dto.TaskResponse;
import com.example.todo.dao.model.Task;
import com.example.todo.dao.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class TaskFacade {

    private final TaskRepoService taskRepoService;

    public TaskResponse createTask(TaskRequest taskRequest) {
            Task task = taskRepoService.toTask(taskRequest);
            return taskRepoService.toTasks(taskRepoService.createTask(task));
    }

    public TaskResponse getTaskById(UUID id) {
        Task task = taskRepoService.getTaskById(id);
        return taskRepoService.toTasks(task);
    }

    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = taskRepoService.getAllTasks();
        return taskRepoService.toTasks(tasks);

    }

    public TaskResponse updateTask(UUID id, TaskRequest taskRequest) {
        Task existingTask = taskRepoService.getTaskById(id);
        taskRepoService.updateTask(taskRequest, existingTask);
        return taskRepoService.toTasks(taskRepoService.createTask(existingTask));
    }

    public TaskResponse updateTaskStatus(UUID id, Boolean status) {
        Task existingTask = taskRepoService.getTaskById(id);
        taskRepoService.updateTaskStatus(status, existingTask);
        return taskRepoService.toTasks(taskRepoService.createTask(existingTask));

    }

    public void deleteTask(UUID id) {
        taskRepoService.delete(id);
    }
}


