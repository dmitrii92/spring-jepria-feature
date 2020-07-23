package com.jepria.springstarter.featureprocess.controller;

import com.jepria.springstarter.featureprocess.dto.FeatureProcessDto;
import com.jepria.springstarter.featureprocess.model.FeatureProcessCreate;
import com.jepria.springstarter.featureprocess.service.FeatureProcessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/feature/{featureId}/")
public class FeatureProcessController {

  private FeatureProcessService service;

  public FeatureProcessController(FeatureProcessService service) {
    this.service = service;
  }

  @GetMapping("feature-process/{featureProcessId}")
  public ResponseEntity<FeatureProcessDto> getFeatureProcessByID(@PathVariable Integer featureId, @PathVariable String featureProcessId) {
    return new ResponseEntity<>(service.getById(featureProcessId), HttpStatus.OK);
  }

  @DeleteMapping("feature-process/{featureProcessId}")
  public ResponseEntity deleteFeatureProcessByID(@PathVariable Integer featureId, @PathVariable String featureProcessId) {
    return null;
  }

  @PostMapping("feature-process")
  public ResponseEntity createFeatureProcessByID(@RequestBody FeatureProcessCreate featureProcessCreate, @PathVariable Integer featureId) {
    service.create(featureId, featureProcessCreate.getFeatureStatusCode());
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("feature-process")
  public ResponseEntity<List<FeatureProcessDto>> findFeatureProcess(@PathVariable Integer featureId) {
    return new ResponseEntity<>(service.getAllByFeatureId(featureId), HttpStatus.OK);
  }

}
