package com.dustbuster.dto.response;



import com.dustbuster.dto.Address;
import com.dustbuster.dto.AvailableSlot;
import com.dustbuster.dto.CalculateQuotation;
import com.dustbuster.dto.CleaningArea;
import com.dustbuster.dto.HowDidUHearUs;
import com.dustbuster.dto.TypeofResidence;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CleaningServiceResponse {

  private String fullName;
  private String email;
  private String phone;
  private Address address;
  private HowDidUHearUs howDidUHearUs;
  private TypeofResidence typeOfResidence;
  private CleaningArea cleaningArea;
  private AvailableSlot availableSlot;
  private CalculateQuotation calculateQuotation;
  private  String additionalComments;
}
