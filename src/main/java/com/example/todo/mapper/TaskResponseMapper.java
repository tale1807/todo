package com.example.todo.mapper;

import com.example.todo.dao.model.Task;
import com.example.todo.dto.TaskResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskResponseMapper {

    TaskResponse toTasks(Task task);

    List<TaskResponse> toTasks(List<Task> tasks);


}
