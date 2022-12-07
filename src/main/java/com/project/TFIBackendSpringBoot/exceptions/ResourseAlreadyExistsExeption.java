package com.project.TFIBackendSpringBoot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourseAlreadyExistsExeption extends Exception{

    public ResourseAlreadyExistsExeption(String message){
        super(message);
    }

}
