package com.dustbuster.exeption;

import lombok.Getter;

@Getter
public class AdapterException extends RuntimeException {

  private final ErrorMessage errorMessage;

  public AdapterException(ErrorMessage errorMessage) {
    super(errorMessage.getErrors().get(0).getMessage());
    this.errorMessage = errorMessage;
  }
}
