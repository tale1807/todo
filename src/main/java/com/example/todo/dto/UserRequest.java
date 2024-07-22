package com.example.todo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {

    @NotNull(message = "Name cannot be empty")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String name;

    @NotNull(message = "Email cannot be empty")
    @Email
    private String email;

    @NotNull(message = "Password cannot be empty")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String password;
}
