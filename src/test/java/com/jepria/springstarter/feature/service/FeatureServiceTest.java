package com.jepria.springstarter.feature.service;

import com.jepria.springstarter.feature.dto.FeatureCreateDto;
import com.jepria.springstarter.feature.model.Feature;
import com.jepria.springstarter.feature.repository.FeatureRepo;
import com.jepria.springstarter.featureprocess.service.FeatureProcessService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeatureServiceTest {

  private FeatureService service;

  @Mock
  FeatureRepo repo;

  @Mock
  FeatureProcessService featureProcessService;

  @Test
  void create() {
    Feature featureExpected = new Feature();
    featureExpected.setFeatureName("FeatureName");
    featureExpected.setFeatureNameEn("FeatureNameEn");
    featureExpected.setDescription("Description");
    featureExpected.setDateIns(new Date());
    featureExpected.setFeatureId(10);

    when(repo.save(Mockito.any())).thenReturn(featureExpected);
    FeatureService service = new FeatureService(repo, featureProcessService);
    FeatureCreateDto featureCreateDto =
        FeatureCreateDto.builder()
            .featureName("FeatureName")
            .featureNameEn("FeatureNameEn")
            .description("Description")
            .build();

    Integer featureId = service.create(featureCreateDto);
    Assertions.assertEquals(featureExpected.getFeatureId(), featureId, "Error create feature, wrong featureID");
  }

  @Test
  void get() {
  }

  @Test
  void update() {
  }

  @Test
  void delete() {
  }

  @Test
  void getAll() {
  }

  @Test
  void getStatusOptions() {
  }

  @Test
  void setStatusOptions() {
  }

  @Test
  void postSearch() {
  }

  @Test
  void getResultSetSize() {
  }

  @Test
  void getResultSet() {
  }

}