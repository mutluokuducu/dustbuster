package com.dustbuster.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalculateQuotation {
  private BigDecimal radiator;
  private BigDecimal bathRoom;
  private BigDecimal wc;
  private BigDecimal discount;
  private BigDecimal total;

}
