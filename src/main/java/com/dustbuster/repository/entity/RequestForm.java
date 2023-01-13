package com.dustbuster.repository.entity;

import static jakarta.persistence.EnumType.STRING;

import com.dustbuster.dto.HowDidUHearUs;
import com.dustbuster.dto.TypeofResidence;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
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
@Table(name = "request_form")
public class RequestForm {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "request_id", unique = true, nullable = false)
  private Long id;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "phone", nullable = false)
  private String phone;

  @Column(name = "how_did_u_hearUs", nullable = false)
  @Enumerated(STRING)
  private HowDidUHearUs howDidUHearUs;

  @Column(name = "type_of_residence", nullable = false)
  @Enumerated(STRING)
  private TypeofResidence typeOfResidence;

  @Column(name = "additional_comments", nullable = false)
  private String additionalComments;

  @Column(name = "progress_status", nullable = false)
  @Enumerated(STRING)
  private ProgressStatus progressStatus;

  @Column(name = "request_date_time")
  private LocalDateTime requestDateTime;

  @Column(name = "update_date_time")
  private LocalDateTime updateDateTime;

  @OneToOne(mappedBy = "requestForm", cascade = CascadeType.ALL)
  private Address address;

  @OneToOne(mappedBy = "requestForm", cascade = CascadeType.ALL)
  private AvailableSlot availableSlot;

  @OneToOne(mappedBy = "requestForm", cascade = CascadeType.ALL)
  private CalculateQuotation calculateQuotation;

  @OneToMany(mappedBy = "requestForm", cascade = CascadeType.ALL)
  private List<CleaningArea> cleaningArea;
}
