package com.jepria.springstarter.featureprocess.dto;

import java.util.Date;

public class FeatureProcessDto {
  private Integer featureProcessId;
  private Integer featureId;
  private String featureStatusCode;
  private String featureStatusName;
  private Date dateIns;

  public Integer getFeatureProcessId() {
    return featureProcessId;
  }

  public void setFeatureProcessId(Integer featureProcessId) {
    this.featureProcessId = featureProcessId;
  }

  public Integer getFeatureId() {
    return featureId;
  }

  public void setFeatureId(Integer featureId) {
    this.featureId = featureId;
  }

  public String getFeatureStatusCode() {
    return featureStatusCode;
  }

  public void setFeatureStatusCode(String featureStatusCode) {
    this.featureStatusCode = featureStatusCode;
  }

  public String getFeatureStatusName() {
    return featureStatusName;
  }

  public void setFeatureStatusName(String featureStatusName) {
    this.featureStatusName = featureStatusName;
  }

  public Date getDateIns() {
    return dateIns;
  }

  public void setDateIns(Date dateIns) {
    this.dateIns = dateIns;
  }
}
