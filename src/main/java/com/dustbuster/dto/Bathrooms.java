package com.dustbuster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bathrooms {
  private BathroomsType bathroomsType;
  private  int bathroomCount;
  private int dirtyLevel;

}
