package com.jepria.springstarter.feature.service;

import com.jepria.springstarter.feature.model.Feature;
import com.jepria.springstarter.feature.model.FeatureSearch;
import com.jepria.springstarter.feature.model.SearchRequest;
import com.jepria.springstarter.feature.repos.FeatureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeatureService {
  @Autowired
  private FeatureRepo repo;

  private String searchId;
  private List<Feature> foundedFeatures = new ArrayList<Feature>();

  public FeatureService(FeatureRepo repo) {
    this.repo = repo;
  }

  public String postSearch(SearchRequest<FeatureSearch> searchRequestDto) {

    FeatureSearch templateFeature = searchRequestDto.getTemplate();

    searchId = "searchID";

    foundedFeatures.clear();

    if (null != templateFeature.getFeatureId()) {
      Optional<Feature> feature =  repo.findById(templateFeature.getFeatureId());
      feature.ifPresent(value -> foundedFeatures.add(value));
    } else {
      List<Feature> features = (List<Feature>) repo.findAll();
      foundedFeatures.addAll(features);
    }

    return searchId;
  }

  public Integer getResultSetSize(String searchId) {
    return foundedFeatures.size();
  }

  public List<Feature> getResultSet(String searchId) {
    return foundedFeatures;
  }

}
