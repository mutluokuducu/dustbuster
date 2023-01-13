package com.dustbuster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String buildingNumber;
    private String line1;
    private String line2;
    private String line3;
    private String line4;
    private String city;
    private String postcode;
    private String county;
    private String country;
}
