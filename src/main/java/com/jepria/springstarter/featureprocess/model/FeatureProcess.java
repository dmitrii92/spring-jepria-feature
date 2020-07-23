package com.jepria.springstarter.featureprocess.model;

import com.jepria.springstarter.feature.model.FeatureStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
public class FeatureProcess {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer featureProcessId;
  private Integer featureId;
  @OneToOne
  private FeatureStatus featureStatus;
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

  public Date getDateIns() {
    return dateIns;
  }

  public void setDateIns(Date dateIns) {
    this.dateIns = dateIns;
  }

  public FeatureStatus getFeatureStatus() {
    return featureStatus;
  }

  public void setFeatureStatus(FeatureStatus featureStatus) {
    this.featureStatus = featureStatus;
  }
}
