package com.jepria.springstarter.feature.controller;

import com.jepria.springstarter.feature.model.*;
import com.jepria.springstarter.feature.repos.FeatureRepo;
import com.jepria.springstarter.feature.repos.FeatureStatusRepo;
import com.jepria.springstarter.feature.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/feature")
public class FeatureController {

  private FeatureRepo featureRepo;
  private FeatureStatusRepo featureStatusRepo;
  private FeatureService service;

  @Autowired
  public FeatureController(FeatureRepo featureRepo, FeatureStatusRepo featureStatusRepo, FeatureService service) {
    this.featureRepo = featureRepo;
    this.featureStatusRepo = featureStatusRepo;
    this.service = service;
  }

  @GetMapping("/{featureId}")
  public ResponseEntity<Feature> getFeature(@PathVariable Integer featureId) {
    Optional<Feature> feature = featureRepo.findById(featureId);

    return new ResponseEntity<>(feature.get(), HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity create(@RequestBody FeatureCreate featureCreate) {
    Integer featureId = service.create(featureCreate);

    HttpHeaders headers = new HttpHeaders();
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .buildAndExpand(featureId).toUri(); //TODO
    headers.setLocation(location);
/*    List<String> exposeHeaders = new ArrayList<String>();
    exposeHeaders.add("Location");*/

    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @PutMapping("/{featureId}")
  public ResponseEntity updateFeature(@PathVariable Integer featureId, @RequestBody FeatureUpdate updateFeature) {
    Optional<Feature> featureOptional = featureRepo.findById(featureId);
    Feature feature = featureOptional.get();
    feature.setFeatureName(updateFeature.getFeatureName());
    feature.setFeatureNameEn(updateFeature.getFeatureNameEn());
    feature.setDescription(updateFeature.getDescription());

    featureRepo.save(feature);

    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping("")
  public ResponseEntity<Iterable<Feature>> getFeatures() {
    Iterable<Feature> features = featureRepo.findAll();

    return new ResponseEntity<>(features, HttpStatus.OK);
  }

  @PostMapping("search")
  public ResponseEntity postSearch(@RequestBody SearchRequest<FeatureSearch> searchRequestDto) {
    String searchId = service.postSearch(searchRequestDto);
    HttpHeaders headers = new HttpHeaders();

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .buildAndExpand(searchId).toUri(); //TODO
    headers.setLocation(location);
    List<String> exposeHeaders = new ArrayList<String>();
    exposeHeaders.add("Location");
    headers.setAccessControlExposeHeaders(exposeHeaders);
    ResponseEntity response = new ResponseEntity<>(headers, HttpStatus.CREATED);
    return response;
  }

  @GetMapping("search/{searchId}/resultset-size")
  public ResponseEntity<Integer> getResultSetSize(@PathVariable String searchId) {
    return new ResponseEntity<>(service.getResultSetSize(searchId), HttpStatus.OK);
  }

  @GetMapping("search/{searchId}/resultset")
  public ResponseEntity<List<Feature>> getResultSet(@PathVariable String searchId, @PathParam("pageSize") Integer pageSize, @PathParam("page") Integer page) {
    return new ResponseEntity<>(service.getResultSet(searchId), HttpStatus.OK);
  }

  @GetMapping("option/feature-status")
  public ResponseEntity<List<FeatureStatus>> getFeatureStatus() {
    List<FeatureStatus> featureStatus = (List<FeatureStatus>) featureStatusRepo.findAll();
    return new ResponseEntity<>(featureStatus, HttpStatus.OK);
  }

}

