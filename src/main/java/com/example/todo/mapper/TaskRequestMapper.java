package com.example.todo.mapper;

import com.example.todo.dao.model.Task;
import com.example.todo.dto.TaskRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskRequestMapper {


    Task toTask(TaskRequest taskRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void toTask(TaskRequest taskRequest, @MappingTarget Task task);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "title", ignore = true)
    @Mapping(target = "description", ignore = true)
    void updateTaskStatusFromRequest(Boolean status, @MappingTarget Task task);

}
