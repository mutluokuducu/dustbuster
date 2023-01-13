package com.dustbuster.exeption;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
public enum ErrorType {
  INTERNAL_ERROR(1000, "An internal server error occurred", INTERNAL_SERVER_ERROR),
  INVALID_PARAMETER_ERROR(1001, "Invalid field(s) or blank field(s) ", UNPROCESSABLE_ENTITY),
  UNKNOWN_RESPONSE_RECEIVED(1002 ,"Unknown response received", BAD_REQUEST),
  WRONG_CUSTOMER_CONSENT_ACTION(1003,
      "Requested Action is not applicable to the current state", BAD_REQUEST);

  private int id;
  private String description;
  private HttpStatus httpStatus;

  ErrorType(int id, String description, HttpStatus httpStatus) {

    this.id = id;
    this.description = description;
    this.httpStatus = httpStatus;
  }
}
