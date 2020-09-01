package com.jepria.springstarter.feature.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeatureDto {
  private Integer featureId;
  private String featureName;
  private String featureNameEn;
  private String description;
  private FeatureStatusDto featureStatus;
  private Date dateIns;
  private Integer authorId;
  private Integer responsibleId;
}
