package com.jepria.springstarter.feature.repos;

import com.jepria.springstarter.feature.model.Feature;
import org.springframework.data.repository.CrudRepository;

public interface FeatureRepo  extends CrudRepository<Feature, Integer> {

}
