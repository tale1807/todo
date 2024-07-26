package com.example.todo.dao.service;

import com.example.todo.dao.exception.TaskCreationException;
import com.example.todo.dao.exception.TaskNotFoundException;
import com.example.todo.dao.model.Task;
import com.example.todo.dao.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class TaskRepoService {

    private final TaskRepository taskRepository;

    public Task createTask(Task task) {
        try{
            return taskRepository.save(task);
        } catch (Exception e){
            throw new TaskCreationException(format("Ошибка при создании задачи: %s", e.getMessage()));
        }
    }

    public Task getTaskById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(format("Не найдена задача по заданному id - %s", id)));
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


}


