package com.jepria.springstarter.feature.controller;

import com.jepria.springstarter.feature.model.*;
import com.jepria.springstarter.feature.repos.FeatureRepo;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/feature")
public class FeatureController {

  @Autowired
  private FeatureRepo repo;

  @Autowired
  private FeatureService service;

  public FeatureController(FeatureRepo repo, FeatureService service) {
    this.repo = repo;
    this.service = service;
  }

  @GetMapping("/{featureId}")
  public ResponseEntity<Feature> getFeature(@PathVariable Integer featureId) {
    Optional<Feature> feature = repo.findById(featureId);

    return new ResponseEntity<>(feature.get(), HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity<Feature> create(@RequestBody FeatureCreate featureCreate) {
    Feature feature = new Feature();
    feature.setFeatureName(featureCreate.getFeatureName());
    feature.setFeatureNameEn(featureCreate.getFeatureNameEn());
    feature.setDescription(featureCreate.getDescription());
    feature.setDateIns(new Date());
    repo.save(feature);
    return new ResponseEntity<>(feature, HttpStatus.CREATED);
  }

  @PutMapping("/{featureId}")
  public ResponseEntity updateFeature(@PathVariable Integer featureId, @RequestBody FeatureUpdate updateFeature) {
    Optional<Feature> featureOptional = repo.findById(featureId);
    Feature feature = featureOptional.get();
    feature.setFeatureName(updateFeature.getFeatureName());
    feature.setFeatureNameEn(updateFeature.getFeatureNameEn());
    feature.setDescription(updateFeature.getDescription());

    repo.save(feature);

    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping("")
  public ResponseEntity<Iterable<Feature>> getFeatures() {
    Iterable<Feature> features = repo.findAll();

    return new ResponseEntity<>(features, HttpStatus.OK);
  }

  @PostMapping("search")
  public ResponseEntity postSearch(@RequestBody SearchRequest<FeatureSearch> searchRequestDto) {
    String searchId =  service.postSearch(searchRequestDto);
    HttpHeaders headers = new HttpHeaders();

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .buildAndExpand(searchId).toUri();
    headers.setLocation(location);
    List<String> exposeHeaders = new ArrayList<String>();
    exposeHeaders.add("Location");
    headers.setAccessControlExposeHeaders(exposeHeaders);
    ResponseEntity response =  new ResponseEntity<>(headers, HttpStatus.CREATED);
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

}

