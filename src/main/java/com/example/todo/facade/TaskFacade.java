package com.example.todo.facade;

import com.example.todo.dao.service.TaskRepoService;
import com.example.todo.dto.TaskRequest;
import com.example.todo.dto.TaskResponse;
import com.example.todo.dao.model.Task;
import com.example.todo.mapper.TaskRequestMapper;
import com.example.todo.mapper.TaskResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskFacade {

    private final TaskRepoService taskRepoService;
    private final TaskResponseMapper taskResponseMapper;
    private final TaskRequestMapper taskRequestMapper;

    public TaskResponse createTask(TaskRequest taskRequest) {
            Task task = taskRequestMapper.toTask(taskRequest);
            return taskResponseMapper.toTasks(taskRepoService.createTask(task));
    }

    public TaskResponse getTaskById(UUID id) {
        Task task = taskRepoService.getTaskById(id);
        return taskResponseMapper.toTasks(task);
    }

    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = taskRepoService.getAllTasks();
        return taskResponseMapper.toTasks(tasks);

    }

    public TaskResponse updateTask(UUID id, TaskRequest taskRequest) {
        Task existingTask = taskRepoService.getTaskById(id);
        taskRequestMapper.toTask(taskRequest, existingTask);
        return taskResponseMapper.toTasks(taskRepoService.createTask(existingTask));
    }

    public TaskResponse updateTaskStatus(UUID id, Boolean status) {
        Task existingTask = taskRepoService.getTaskById(id);
        taskRequestMapper.updateTaskStatusFromRequest(status, existingTask);
        return taskResponseMapper.toTasks(taskRepoService.createTask(existingTask));
    }

    public void deleteTask(UUID id) {
        taskRepoService.delete(id);
    }
}


