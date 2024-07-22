package com.example.todo.dao.mapper;

import com.example.todo.dao.model.Task;
import com.example.todo.dto.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskResponseMapper {

    TaskResponseMapper INSTANCE = Mappers.getMapper(TaskResponseMapper.class);

    TaskResponse toTasks(Task task);

    List<TaskResponse> toTasks(List<Task> tasks);


}
