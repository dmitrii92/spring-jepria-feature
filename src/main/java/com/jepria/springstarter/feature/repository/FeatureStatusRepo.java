package com.jepria.springstarter.feature.repository;

import com.jepria.springstarter.feature.model.FeatureStatus;
import org.springframework.data.repository.CrudRepository;

public interface FeatureStatusRepo extends CrudRepository<FeatureStatus, Integer> {
}
