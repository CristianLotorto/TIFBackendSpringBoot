package com.project.TFIBackendSpringBoot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException{

    @ExceptionHandler({ResourseNotFoundException.class})
    public ResponseEntity<String> processErrorNotFound(ResourseNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler({ResourseAlreadyExistsExeption.class})
    public ResponseEntity<String> processErrorAlreadyExists(ResourseAlreadyExistsExeption e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

}
