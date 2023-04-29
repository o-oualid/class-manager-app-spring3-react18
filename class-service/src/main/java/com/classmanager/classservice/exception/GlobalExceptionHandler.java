package com.classmanager.classservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@EnableWebMvc
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        ApiError notValidError = new ApiError(errors);
        return new ResponseEntity<>(notValidError, new HttpHeaders(), ex.getStatusCode());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        ApiError userNotFoundError = new ApiError(errors, HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(userNotFoundError, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public ResponseEntity<ApiError> handleDuplicateKeyException(DuplicateKeyException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        DuplicateKeyException duplicateKeyException = new DuplicateKeyException(errors);
        return new ResponseEntity<>(duplicateKeyException, new HttpHeaders(), HttpStatus.CONFLICT);
    }
/*
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex) {

    }

 */

    @ExceptionHandler({Exception.class, RuntimeException.class, SQLException.class, UncaughtException.class})
    public ResponseEntity<UncaughtException> handleAllUncaughtException(Exception ex){
        List<String> errors = getErrorsList(ex);
        UncaughtException uncaughtException = new UncaughtException(errors);
        return new ResponseEntity<>(uncaughtException , new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private List<String> getErrorsList(Exception ex) {
        if (ex instanceof BindException) {
            List<ObjectError> objectErrors = ((BindException) ex).getAllErrors();
            return objectErrors.stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList();
        }
        return Collections.singletonList(ex.getMessage());
    }

}