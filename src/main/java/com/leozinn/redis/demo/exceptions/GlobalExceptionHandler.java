package com.leozinn.redis.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.leozinn.redis.demo.error.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UnprocessableEntity.class)
  public ResponseEntity<ErrorResponse> handleUnprocessableEntity(UnprocessableEntity ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler(KeyNotFound.class)
  public ResponseEntity<ErrorResponse> handleKeyNotFound(KeyNotFound ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
}
