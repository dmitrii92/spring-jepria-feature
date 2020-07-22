package com.jepria.springstarter.featureprocess.controller;

import com.jepria.springstarter.featureprocess.model.FeatureProcess;
import com.jepria.springstarter.featureprocess.model.FeatureProcessCreate;
import com.jepria.springstarter.featureprocess.repos.FeatureProcessRepo;
import com.jepria.springstarter.featureprocess.service.FeatureProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/feature/{featureId}/")
public class FeatureProcessController {

  @Autowired
  private FeatureProcessRepo repo;

  @Autowired
  private FeatureProcessService service;

  public FeatureProcessController(FeatureProcessRepo featureProcessRepo, FeatureProcessService service) {
    this.repo = featureProcessRepo;
    this.service = service;
  }

  @GetMapping("feature-process/{featureProcessId}")
  public FeatureProcess getFeatureProcessByID(@PathVariable Integer featureId, @PathVariable String featureProcessId) {
    repo.findById(featureProcessId);
    return null;
  }

  @DeleteMapping("feature-process/{featureProcessId}")
  public FeatureProcess deleteFeatureProcessByID(@PathVariable Integer featureId, @PathVariable String featureProcessId) {
    return null;
  }

  @PostMapping("feature-process")
  public ResponseEntity<FeatureProcess> createFeatureProcessByID(@RequestBody FeatureProcessCreate featureProcessCreate, @PathVariable Integer featureId) {
    service.create(featureId, featureProcessCreate.getFeatureStatusCode());
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("feature-process")
  public ResponseEntity<List<FeatureProcess>> findFeatureProcess(@PathVariable Integer featureId) {
    List<FeatureProcess> featureProcessList = repo.findAllByFeatureId(featureId);
    return new ResponseEntity<>(featureProcessList, HttpStatus.OK);
  }

}
