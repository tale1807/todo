package com.example.todo.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TaskErrorResponse {
    private String message;
    private Long timestamp;

}
