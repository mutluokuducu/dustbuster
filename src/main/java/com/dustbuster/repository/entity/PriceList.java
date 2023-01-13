package com.dustbuster.repository.entity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
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
@Table(name = "price_list")
public class PriceList {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @Column(name = "name", nullable = false)
  @Enumerated(STRING)
  private CleaningName name;
  @Column(name = "type", nullable = false)
  private String type;
  @Column(name = "price", nullable = false)
  private BigDecimal price;
//  @Column(name = "date_time", nullable = false)
  private LocalDateTime localDateTime;
}
