package com.example.todo.dao.exception;

import com.example.todo.util.TaskErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskExceptionHandler {



    @ExceptionHandler
    private ResponseEntity<TaskErrorResponse> handleException(TaskNotFoundException e) {

        TaskErrorResponse response = new TaskErrorResponse(
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
