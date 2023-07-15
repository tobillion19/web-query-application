package com.genesys.weatherqueryapplication.exception;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger =  LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(InvalidDataTypeException.class)
    public ResponseEntity<String> handleInvalidDataTypeException(InvalidDataTypeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex){

        logger.error("An error occurred: ", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred. Please try again");
    }

}
