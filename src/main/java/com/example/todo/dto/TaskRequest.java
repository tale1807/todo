package com.example.todo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NonNull
public class TaskRequest {

    @NotNull(message = "Title cannot be empty")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @Size(max = 1000, message = "Description can be up to 1000 characters")
    private String description;

    @NotNull(message = "Status cannot be empty")
    private Boolean status;
}
