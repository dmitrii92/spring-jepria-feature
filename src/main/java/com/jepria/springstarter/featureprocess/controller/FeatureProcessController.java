package com.jepria.springstarter.featureprocess.controller;

import com.jepria.springstarter.featureprocess.dto.FeatureProcessDto;
import com.jepria.springstarter.featureprocess.model.FeatureProcessCreate;
import com.jepria.springstarter.featureprocess.service.FeatureProcessService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
  public ResponseEntity<FeatureProcessDto> getFeatureProcessByID(@PathVariable Integer featureId, @PathVariable Integer featureProcessId) {
    return new ResponseEntity<>(service.getById(featureProcessId), HttpStatus.OK);
  }

  @DeleteMapping("feature-process/{featureProcessId}")
  public ResponseEntity deleteFeatureProcessByID(@PathVariable Integer featureId, @PathVariable Integer featureProcessId) {
    service.delete(featureProcessId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("feature-process")
  public ResponseEntity createFeatureProcessByID(@RequestBody FeatureProcessCreate featureProcessCreate, @PathVariable Integer featureId) {
    Integer featureProcessId = service.create(featureId, featureProcessCreate.getFeatureStatusCode());

    HttpHeaders headers = new HttpHeaders();
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(featureProcessId).toUri();
    headers.setLocation(location);

    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @GetMapping("feature-process")
  public ResponseEntity<List<FeatureProcessDto>> findFeatureProcess(@PathVariable Integer featureId) {
    return new ResponseEntity<>(service.getAllByFeatureId(featureId), HttpStatus.OK);
  }

}
