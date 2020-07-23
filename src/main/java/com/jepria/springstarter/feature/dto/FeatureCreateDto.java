package com.jepria.springstarter.feature.dto;

public class FeatureCreateDto {
  private String featureName;
  private String featureNameEn;
  private String description;

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
}
