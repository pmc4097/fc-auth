package com.example.fcauth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Getter
@Entity
public class Department {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(example = "1", description = "부서ID")
  private Long id;

  @Schema(example = "인사팀", description = "부서명")
  private String departmentName;

  @Schema(example = "1", description = "담당 조직장 임직원 ID")
  @OneToOne
  @JoinColumn(name = "team_lead_id", referencedColumnName = "id")
  private Employee teamLead;
}
