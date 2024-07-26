package com.example.todo.mapper;

import com.example.todo.dao.model.User;
import com.example.todo.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    UserResponse toUsers(User user);

    @Mapping(target = "tasks", ignore = true)
    List<UserResponse> toUsers(List<User> users);
}
