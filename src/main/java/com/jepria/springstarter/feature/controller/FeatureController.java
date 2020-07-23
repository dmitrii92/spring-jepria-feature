package com.jepria.springstarter.feature.controller;

import com.jepria.springstarter.feature.dto.*;
import com.jepria.springstarter.feature.model.*;
import com.jepria.springstarter.feature.repository.FeatureStatusRepo;
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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/feature")
public class FeatureController {

  private FeatureStatusRepo featureStatusRepo;
  private FeatureService service;

  @Autowired
  public FeatureController(FeatureStatusRepo featureStatusRepo, FeatureService service) {
    this.featureStatusRepo = featureStatusRepo;
    this.service = service;
  }

  @GetMapping("/{featureId}")
  public ResponseEntity<Feature> getFeature(@PathVariable Integer featureId) {
    Feature feature = service.get(featureId);
    return new ResponseEntity<>(feature, HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity create(@RequestBody FeatureCreateDto featureCreate) {
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
  public ResponseEntity updateFeature(@PathVariable Integer featureId, @RequestBody FeatureUpdateDto updateFeature) {
    if (service.update(featureId, updateFeature)) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{featureId}")
  public ResponseEntity deleteFeature(@PathVariable Integer featureId) {
    service.delete(featureId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("")
  public ResponseEntity<List<FeatureDto>> getFeatures() {
    return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
  }

  @PostMapping("search")
  public ResponseEntity postSearch(@RequestBody SearchRequest<FeatureSearchDto> searchRequestDto) {
    String searchId = service.postSearch(searchRequestDto);
    HttpHeaders headers = new HttpHeaders();

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .buildAndExpand(searchId).toUri(); //TODO
    headers.setLocation(location);
    List<String> exposeHeaders = new ArrayList<String>();
    exposeHeaders.add("Location");
    headers.setAccessControlExposeHeaders(exposeHeaders);
    return new ResponseEntity<>(headers, HttpStatus.CREATED);
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

