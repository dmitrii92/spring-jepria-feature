package com.jepria.springstarter.feature.service;

import com.jepria.springstarter.feature.model.Feature;
import com.jepria.springstarter.feature.model.FeatureCreate;
import com.jepria.springstarter.feature.model.FeatureSearch;
import com.jepria.springstarter.feature.model.SearchRequest;
import com.jepria.springstarter.feature.repos.FeatureRepo;
import com.jepria.springstarter.featureprocess.service.FeatureProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FeatureService {

  private FeatureRepo featureRepo;
  private FeatureProcessService featureProcessService;

  private String searchId;
  private List<Feature> foundedFeatures = new ArrayList<Feature>();

  public FeatureService(FeatureRepo featureRepo, FeatureProcessService featureProcessService) {
    this.featureRepo = featureRepo;
    this.featureProcessService = featureProcessService;
  }

  public String postSearch(SearchRequest<FeatureSearch> searchRequestDto) {

    FeatureSearch templateFeature = searchRequestDto.getTemplate();

    searchId = "searchID";

    foundedFeatures.clear();

    if (null != templateFeature.getFeatureId()) {
      Optional<Feature> feature = featureRepo.findById(templateFeature.getFeatureId());
      feature.ifPresent(value -> foundedFeatures.add(value));
    } else {
      List<Feature> features = (List<Feature>) featureRepo.findAll();
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

  public Integer create(FeatureCreate featureCreate) {
    Feature feature = new Feature();
    feature.setFeatureName(featureCreate.getFeatureName());
    feature.setFeatureNameEn(featureCreate.getFeatureNameEn());
    feature.setDescription(featureCreate.getDescription());
    feature.setDateIns(new Date());

    featureRepo.save(feature);
    featureProcessService.create(feature.getFeatureId(), "NEW");

    return feature.getFeatureId();
  }

}
