package com.dustbuster.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CleaningArea {

  private List<Radiator> radiatorList;
  private List<Bathrooms> bathroomsList;
  private  List<WC> wc;
  private String discountCode;

}
