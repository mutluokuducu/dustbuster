package com.dustbuster.repository.entity;

import static jakarta.persistence.EnumType.STRING;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "cleaning_area")
public class CleaningArea {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  @Enumerated(STRING)
  private CleaningName cleaningName;

  @Column(name = "type")
  @Enumerated(STRING)
  private Type type;

  @Column(name = "count")
  private int count;

  @Column(name = "dirty_level")
  private int dirtyLevel;

  @ManyToOne
  @JoinColumn(name = "request_id", nullable = false)
  private RequestForm requestForm;
}
