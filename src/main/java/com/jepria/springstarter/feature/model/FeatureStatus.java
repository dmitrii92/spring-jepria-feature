package com.jepria.springstarter.feature.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FeatureStatus {
  @Id
  private String value;
  private String name;

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
