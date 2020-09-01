package com.jepria.springstarter.featureprocess.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FeatureProcessDto {
  private Integer featureProcessId;
  private Integer featureId;
  private String featureStatusCode;
  private String featureStatusName;
  private Date dateIns;
}
