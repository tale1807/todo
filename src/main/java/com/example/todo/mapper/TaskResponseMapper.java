package com.example.todo.mapper;

import com.example.todo.dao.model.Task;
import com.example.todo.dto.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskResponseMapper {

    @Mapping(source = "user.id", target = "user")
    TaskResponse toTasks(Task task);

    List<TaskResponse> toTasks(List<Task> tasks);


}
