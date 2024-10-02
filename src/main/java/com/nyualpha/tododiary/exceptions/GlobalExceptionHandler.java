package com.nyualpha.tododiary.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.persistence.EntityNotFoundException;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    // Crear una instancia del logger para esta clase
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*
     * All validations errors are handler here
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

        logger.error("Validation error: {}", ex.getMessage(), ex);

        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Validation Error");

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            fieldErrors.put(field, message);
        });

        errorResponse.put("errors", fieldErrors);
        errorResponse.put("timestamp", LocalDateTime.now());


        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    /*
     * when a resourse is not found in database
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex){

        logger.error("Entidad no encontrada: {}", ex.getMessage(), ex);

        Map<String, Object> error = new HashMap<>();
        error.put("status",HttpStatus.NOT_FOUND);
        error.put("message",ex.getMessage());
        error.put("timestamp",LocalDateTime.now());


        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);

    }
}
