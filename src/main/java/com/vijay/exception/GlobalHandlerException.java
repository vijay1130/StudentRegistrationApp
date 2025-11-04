package com.vijay.exception;

import com.vijay.dto.ApiResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandlerException {
    private final Logger logger = LoggerFactory.getLogger(GlobalHandlerException.class);


    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseError> handleResourceNotFoundException(Exception ex){
        logger.error("inside handleResourceNotFoundException {} ",ex.getMessage());
        ApiResponseError apiResponseError = new ApiResponseError();
        apiResponseError.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(apiResponseError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }




}
