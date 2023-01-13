package com.dustbuster.controller;

import static com.dustbuster.constant.Constants.POST_CLEAN_REQUEST_FORM_URL;
import static com.dustbuster.constant.Constants.POST_CLEAN_REQUEST_QUOTA_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.dustbuster.dto.request.CalculateQuotationRequest;
import com.dustbuster.dto.request.CleaningServiceRequest;
import com.dustbuster.dto.response.CalculateResponse;
import com.dustbuster.dto.response.CleaningServiceResponse;
import com.dustbuster.service.CleaningService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CleanController {

  @Autowired private CleaningService cleaningService;

  @PostMapping(value = POST_CLEAN_REQUEST_FORM_URL, consumes = APPLICATION_JSON_VALUE)
  public HttpEntity<CleaningServiceResponse> postRequestForm(
      @RequestBody CleaningServiceRequest cleaningServiceRequest) {

    return ResponseEntity.ok().body(cleaningService.requestForm(cleaningServiceRequest));
  }

  @PostMapping(value = POST_CLEAN_REQUEST_QUOTA_URL, consumes = APPLICATION_JSON_VALUE)
  public HttpEntity<CalculateResponse> postQuotation(
      @RequestBody CalculateQuotationRequest calculateQuotationRequest) {

    return ResponseEntity.ok()
        .body(cleaningService.calculateRequestForm(calculateQuotationRequest));
  }
}
