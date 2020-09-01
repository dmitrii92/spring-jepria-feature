package com.jepria.springstarter.feature.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeatureStatusDto {
  private String value;
  private String name;

  public FeatureStatusDto(String value, String name) {
    this.value = value;
    this.name = name;
  }
}
