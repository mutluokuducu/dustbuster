package com.dustbuster.dto.request;

import com.dustbuster.dto.CleaningArea;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculateQuotationRequest {
  private CleaningArea cleaningArea;
}
