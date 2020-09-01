package com.jepria.springstarter.feature.mapper;

import com.jepria.springstarter.feature.dto.FeatureDto;
import com.jepria.springstarter.feature.model.Feature;

public class FeatureMapper {
  public static FeatureDto toFeatureDto(Feature feature) {
    FeatureDto featureDto =
        FeatureDto.builder()
            .featureId(feature.getFeatureId())
            .featureName(feature.getFeatureName())
            .featureNameEn(feature.getFeatureNameEn())
            .description(feature.getDescription())
            .dateIns(feature.getDateIns())
            .authorId(feature.getAuthorId())
            .responsibleId(feature.getResponsibleId())
            .build();
    return featureDto;
  }
}
