package com.jepria.springstarter.feature.mapper;

import com.jepria.springstarter.feature.dto.FeatureDto;
import com.jepria.springstarter.feature.model.Feature;

public class FeatureMapper {
  public static FeatureDto toFeatureDto(Feature feature) {
    FeatureDto featureDto = new FeatureDto();
    featureDto.setFeatureId(feature.getFeatureId());
    featureDto.setFeatureName(feature.getFeatureName());
    featureDto.setFeatureNameEn(feature.getFeatureNameEn());
    featureDto.setDescription(feature.getDescription());
    featureDto.setDateIns(feature.getDateIns());
    featureDto.setAuthorId(feature.getAuthorId());
    featureDto.setResponsibleId(feature.getResponsibleId());
    return featureDto;
  }
}
