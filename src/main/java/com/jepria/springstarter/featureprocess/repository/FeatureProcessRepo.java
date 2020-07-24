package com.jepria.springstarter.featureprocess.repository;

import com.jepria.springstarter.featureprocess.model.FeatureProcess;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeatureProcessRepo extends CrudRepository<FeatureProcess, Integer> {
  List<FeatureProcess> findAllByFeatureId(Integer featureId);
  FeatureProcess findFirstByFeatureIdOrderByFeatureProcessIdDesc(Integer featureId);
}
