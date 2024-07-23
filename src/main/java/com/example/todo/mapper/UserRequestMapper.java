package com.example.todo.mapper;

import com.example.todo.dao.model.User;
import com.example.todo.dto.UserRequest;
import com.example.todo.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {

    User toUser(UserRequest userRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    void toUser(UserRequest userRequest, @MappingTarget User user);

}
