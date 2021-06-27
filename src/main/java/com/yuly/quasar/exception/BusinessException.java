package com.yuly.quasar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Business error")
public class BusinessException extends RuntimeException {

  public BusinessException(String message) {
    super(message);
  }
}
