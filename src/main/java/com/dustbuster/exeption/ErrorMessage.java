package com.dustbuster.exeption;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

  private HttpStatus httpStatus;
  private List<java.lang.Error> errors;

}
