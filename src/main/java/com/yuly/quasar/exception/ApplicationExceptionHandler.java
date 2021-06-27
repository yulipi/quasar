package com.yuly.quasar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<Error> handleException(BusinessException e) {
    Error err = new Error(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    return new ResponseEntity<>(err, err.getHttpStatus());
  }
}
