package com.dustbuster.service;

import com.dustbuster.dto.request.CalculateQuotationRequest;
import com.dustbuster.dto.request.CleaningServiceRequest;
import com.dustbuster.dto.response.CalculateResponse;
import com.dustbuster.dto.response.CleaningServiceResponse;

public interface CleaningService {

  CleaningServiceResponse requestForm(CleaningServiceRequest cleaningServiceRequest);

  CalculateResponse calculateRequestForm(CalculateQuotationRequest calculateQuotationRequest);
}
