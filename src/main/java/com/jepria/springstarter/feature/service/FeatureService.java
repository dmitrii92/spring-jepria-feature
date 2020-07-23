package com.jepria.springstarter.feature.service;

import com.jepria.springstarter.feature.dto.*;
import com.jepria.springstarter.feature.mapper.FeatureMapper;
import com.jepria.springstarter.feature.model.Feature;
import com.jepria.springstarter.feature.model.FeatureStatus;
import com.jepria.springstarter.feature.repository.FeatureRepo;
import com.jepria.springstarter.featureprocess.service.FeatureProcessService;
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
  private List<FeatureDto> foundedFeatures = new ArrayList<>();

  public FeatureService(FeatureRepo featureRepo, FeatureProcessService featureProcessService) {
    this.featureRepo = featureRepo;
    this.featureProcessService = featureProcessService;
  }

  public String postSearch(SearchRequest<FeatureSearchDto> searchRequestDto) {

    FeatureSearchDto templateFeature = searchRequestDto.getTemplate();

    searchId = "searchID";

    foundedFeatures.clear();

    if (null != templateFeature.getFeatureId()) {
      Optional<Feature> feature = featureRepo.findById(templateFeature.getFeatureId());
      feature.ifPresent(value -> foundedFeatures.add(FeatureMapper.toFeatureDto(value)));
    } else {
      List<Feature> features = (List<Feature>) featureRepo.findAll();
      features.forEach(feature -> foundedFeatures.add(FeatureMapper.toFeatureDto(feature)));
    }

    return searchId;
  }

  public Integer getResultSetSize(String searchId) {
    return foundedFeatures.size();
  }

  public List<FeatureDto> getResultSet(String searchId) {
    return foundedFeatures;
  }

  public Integer create(FeatureCreateDto featureCreateDto) {
    Feature feature = new Feature();
    feature.setFeatureName(featureCreateDto.getFeatureName());
    feature.setFeatureNameEn(featureCreateDto.getFeatureNameEn());
    feature.setDescription(featureCreateDto.getDescription());
    feature.setDateIns(new Date());

    featureRepo.save(feature);
    featureProcessService.create(feature.getFeatureId(), "NEW");

    return feature.getFeatureId();
  }

  public FeatureDto get(Integer featureId) {
    Optional<Feature> feature = featureRepo.findById(featureId);
    if (feature.isPresent()) {
      FeatureDto featureDto = FeatureMapper.toFeatureDto(feature.get());
      featureDto.setFeatureStatus(featureProcessService.getLastFeatureStatus(featureId));
      return featureDto;
    }
    return null;
  }

  public boolean update(Integer featureId, FeatureUpdateDto updateDto) {
    Optional<Feature> featureOptional = featureRepo.findById(featureId);
    Feature feature = featureOptional.orElse(null);
    if (null != feature) {
      feature.setFeatureName(updateDto.getFeatureName());
      feature.setFeatureNameEn(updateDto.getFeatureNameEn());
      feature.setDescription(updateDto.getDescription());
      featureRepo.save(feature);
      return true;
    }
    return false;
  }

  public void delete(Integer featureId) {
    featureRepo.deleteById(featureId);
  }

  public List<FeatureDto> getAll() {
    List<FeatureDto> dtos = new ArrayList<>();
    featureRepo.findAll().forEach(feature -> dtos.add(FeatureMapper.toFeatureDto(feature)));
    return dtos;
  }

  public List<FeatureStatusDto> getStatusOptions() {
    return featureProcessService.getStatusOptions();
  }
}
