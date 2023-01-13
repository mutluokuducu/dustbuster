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
import java.math.BigDecimal;
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
@Table(name = "calculate_quotation")
public class CalculateQuotation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "radiator")
  private BigDecimal radiator;

  @Column(name = "bathRoom")
  private BigDecimal bathRoom;

  @Column(name = "wc")
  private BigDecimal wc;

  @Column(name = "discount")
  private BigDecimal discount;

  @Column(name = "total")
  private BigDecimal total;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "request_id", nullable = false)
  private RequestForm requestForm;
}
