package com.jepria.springstarter.feature.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeatureCreateDto {
  private String featureName;

  private String featureNameEn;

  private String description;

}
