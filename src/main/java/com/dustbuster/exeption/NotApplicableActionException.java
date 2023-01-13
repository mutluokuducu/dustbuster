package com.dustbuster.exeption;

import lombok.Getter;

@Getter
public class NotApplicableActionException extends RuntimeException {

  private final ErrorType errorType;

  public NotApplicableActionException(ErrorType errorType) {
    super(errorType.getDescription());
    this.errorType = errorType;
  }
}
