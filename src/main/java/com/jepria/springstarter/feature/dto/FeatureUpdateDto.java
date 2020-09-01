package com.jepria.springstarter.feature.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeatureUpdateDto {
  private String featureName;
  private String featureNameEn;
  private String description;
  private Integer responsibleId;
}
