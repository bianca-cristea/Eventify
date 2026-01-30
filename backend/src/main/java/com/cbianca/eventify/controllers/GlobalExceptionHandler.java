package com.cbianca.eventify.controllers;


import com.cbianca.eventify.dtos.ErrorDTO;
import com.cbianca.eventify.exceptions.UserNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {



    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolation(ConstraintViolationException exc) {
        log.error("Caught ConstraintViolationException", exc);
        ErrorDTO errorDTO = new ErrorDTO();

        String errorMessage = exc.getConstraintViolations().stream().findFirst().map(violation -> violation.getPropertyPath() + ": " + violation.getMessage()).orElse("Constraint violation occurred.");

        errorDTO.setError(errorMessage);

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exc){
        log.error("Caught MethodArgumentNotValidException", exc);
        ErrorDTO errorDTO = new ErrorDTO();

        BindingResult bindingResult = exc.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        String errorMessage = fieldErrorList.stream().findFirst().map(fieldError -> fieldError.getField()+": "+fieldError.getDefaultMessage()).orElse("Validation error occured.");

        errorDTO.setError(errorMessage);

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(Exception exc){
        log.error("Caught UserNotFoundException", exc);
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("User not found");

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception exc) {
        log.error("Caught exception", exc);
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("An unknown error occurred");

        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
