package com.jepria.springstarter.featureprocess.repos;

import com.jepria.springstarter.featureprocess.model.FeatureProcess;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeatureProcessRepo extends CrudRepository<FeatureProcess, String> {
  List<FeatureProcess> findAllByFeatureId(Integer featureId);
}
