package com.dustbuster.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Getter
@Setter
@ToString
@Table(name = "address")
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "building_number")
  private String buildingNumber;

  @Column(name = "line1")
  private String line1;

  @Column(name = "line2")
  private String line2;

  @Column(name = "line3")
  private String line3;

  @Column(name = "line4")
  private String line4;

  @Column(name = "city")
  private String city;

  @Column(name = "post_code")
  private String postcode;

  @Column(name = "county")
  private String county;

  @Column(name = "country")
  private String country;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "request_id", nullable = false)
  private RequestForm requestForm;
}
