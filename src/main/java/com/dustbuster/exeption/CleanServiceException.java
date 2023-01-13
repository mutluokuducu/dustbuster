package com.dustbuster.exeption;

import lombok.Getter;

@Getter
public class CleanServiceException extends RuntimeException {

  private final ErrorType errorType;

  public CleanServiceException(ErrorType errorType) {
    this.errorType = errorType;
  }
}
