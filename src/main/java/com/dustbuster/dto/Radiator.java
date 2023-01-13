package com.dustbuster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Radiator {
  private RadiatorType radiatorType;
  private int radiatorCount;
  private int dirtyLevel;//1 to 5

}
