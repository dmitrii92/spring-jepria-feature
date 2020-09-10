package com.jepria.springstarter.featureprocess.mapper;

import com.jepria.springstarter.featureprocess.dto.FeatureProcessDto;
import com.jepria.springstarter.featureprocess.model.FeatureProcess;

public class FeatureProcessMapper {
  public static FeatureProcessDto toFeatureProcessDto(FeatureProcess featureProcess) {
    FeatureProcessDto dto = new FeatureProcessDto();
    dto.setFeatureId(featureProcess.getFeatureId());
    dto.setFeatureProcessId(featureProcess.getFeatureProcessId());
    dto.setDateIns(featureProcess.getDateIns());
    if (null != featureProcess.getFeatureStatus()) {
      dto.setFeatureStatusCode(featureProcess.getFeatureStatus().getValue());
      dto.setFeatureStatusName(featureProcess.getFeatureStatus().getName());
    }
    return dto;
  }
}
