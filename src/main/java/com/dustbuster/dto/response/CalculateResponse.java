package com.dustbuster.dto.response;

import com.dustbuster.dto.CalculateQuotation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculateResponse {
  private CalculateQuotation calculateQuotation;

}
