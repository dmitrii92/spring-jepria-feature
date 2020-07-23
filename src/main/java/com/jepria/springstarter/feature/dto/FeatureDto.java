package com.jepria.springstarter.feature.dto;

import java.util.Date;

public class FeatureDto {
  private Integer featureId;
  private String featureName;
  private String featureNameEn;
  private String description;
  private FeatureStatusDto featureStatus;
  private Date dateIns;
  private Integer authorId;
  private Integer responsibleId;

  public Integer getFeatureId() {
    return featureId;
  }

  public void setFeatureId(Integer featureId) {
    this.featureId = featureId;
  }

  public String getFeatureName() {
    return featureName;
  }

  public void setFeatureName(String featureName) {
    this.featureName = featureName;
  }

  public String getFeatureNameEn() {
    return featureNameEn;
  }

  public void setFeatureNameEn(String featureNameEn) {
    this.featureNameEn = featureNameEn;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public FeatureStatusDto getFeatureStatus() {
    return featureStatus;
  }

  public void setFeatureStatus(FeatureStatusDto featureStatus) {
    this.featureStatus = featureStatus;
  }

  public Date getDateIns() {
    return dateIns;
  }

  public void setDateIns(Date dateIns) {
    this.dateIns = dateIns;
  }

  public Integer getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Integer authorId) {
    this.authorId = authorId;
  }

  public Integer getResponsibleId() {
    return responsibleId;
  }

  public void setResponsibleId(Integer responsibleId) {
    this.responsibleId = responsibleId;
  }
}
