package com.jepria.springstarter.featureprocess.service;

import com.jepria.springstarter.featureprocess.model.FeatureProcess;
import com.jepria.springstarter.featureprocess.repository.FeatureProcessRepo;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FeatureProcessService {

  private FeatureProcessRepo repo;

  public FeatureProcessService(FeatureProcessRepo repo) {
    this.repo = repo;
  }

  public Integer create(Integer featureId, String featureStatusCode) {
    FeatureProcess featureProcess = new FeatureProcess();
    featureProcess.setDateIns(new Date());
    featureProcess.setFeatureId(featureId);
    featureProcess.setFeatureStatusCode("NEW");
    featureProcess.setFeatureStatusCode(featureStatusCode);
    repo.save(featureProcess);
    return featureProcess.getFeatureProcessId();
  }

}
