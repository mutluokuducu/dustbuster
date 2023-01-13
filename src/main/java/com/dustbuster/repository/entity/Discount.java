package com.dustbuster.repository.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@Table(name = "discount")
public class Discount {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(name = "discount_code")
  private String discountCode;

  @Column(name = "discount_value")
  private BigDecimal discountValue;

  @Column(name = "is_used")
  private boolean isUsed;

  @Column(name = "used_date_time")
  private LocalDateTime usedLocalDateTime;


}
