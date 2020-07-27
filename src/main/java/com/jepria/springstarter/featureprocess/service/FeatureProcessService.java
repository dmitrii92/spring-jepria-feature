package com.jepria.springstarter.featureprocess.service;

import com.jepria.springstarter.feature.dto.FeatureStatusDto;
import com.jepria.springstarter.feature.model.FeatureStatus;
import com.jepria.springstarter.featureprocess.dto.FeatureProcessDto;
import com.jepria.springstarter.featureprocess.mapper.FeatureProcessMapper;
import com.jepria.springstarter.featureprocess.model.FeatureProcess;
import com.jepria.springstarter.featureprocess.repository.FeatureProcessRepo;
import com.jepria.springstarter.featureprocess.repository.FeatureStatusRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FeatureProcessService {

  private FeatureProcessRepo repo;
  private FeatureStatusRepo featureStatusRepo;

  public FeatureProcessService(FeatureProcessRepo repo, FeatureStatusRepo featureStatusRepo) {
    this.repo = repo;
    this.featureStatusRepo = featureStatusRepo;
  }

  public Integer create(Integer featureId, String featureStatusCode) {
    FeatureProcess featureProcess = new FeatureProcess();
    featureProcess.setDateIns(new Date());
    featureProcess.setFeatureId(featureId);
    featureProcess.setFeatureStatus(getStatusByCode(featureStatusCode));

    repo.save(featureProcess);
    return featureProcess.getFeatureProcessId();
  }

  public FeatureProcessDto getById(Integer featureProcessId) {
    return FeatureProcessMapper.toFeatureProcessDto(Objects.requireNonNull(repo.findById(featureProcessId).orElse(null)));
  }

  public List<FeatureProcessDto> getAllByFeatureId(Integer featureId) {
    return repo.findAllByFeatureId(featureId).stream().map(FeatureProcessMapper::toFeatureProcessDto).collect(Collectors.toList());
  }

  public FeatureStatus getStatusByCode(String featureStatusCode) {
    return featureStatusRepo.findById(featureStatusCode).orElse(null);
  }

  public FeatureStatusDto getLastFeatureStatus(Integer featureId) {
    FeatureProcess featureProcess = repo.findFirstByFeatureIdOrderByFeatureProcessIdDesc(featureId);
    if (null != featureProcess) {
      FeatureStatus featureStatus = featureProcess.getFeatureStatus();
      if (null != featureStatus) {
        return new FeatureStatusDto(featureStatus.getValue(), featureStatus.getName());
      }
    }
    return null;
  }

  public List<FeatureStatusDto> getStatusOptions() {
    List<FeatureStatusDto> dtos = new ArrayList<>();
    featureStatusRepo.findAll().forEach(featureStatus -> dtos.add(new FeatureStatusDto(featureStatus.getValue(), featureStatus.getName())));
    return dtos;
  }

  public void setStatusOptions(List<FeatureStatusDto> options) {
    featureStatusRepo.deleteAll();
    options.stream().forEach(featureStatusDto -> {
      FeatureStatus status = new FeatureStatus();
      status.setName(featureStatusDto.getName());
      status.setValue(featureStatusDto.getValue());
      featureStatusRepo.save(status);
    });
  }

  public void delete(Integer featureProcessId) {
    repo.deleteById(featureProcessId);
  }

}
