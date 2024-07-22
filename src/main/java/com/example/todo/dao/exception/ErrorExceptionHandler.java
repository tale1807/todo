package com.example.todo.dao.exception;

import com.example.todo.util.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e){
        UserErrorResponse userErrorResponse = new UserErrorResponse(
                e.getMessage()
        );
        return new ResponseEntity<>(userErrorResponse, HttpStatus.NOT_FOUND);
    }

}
