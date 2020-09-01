package com.jepria.springstarter.feature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jepria.springstarter.feature.dto.FeatureCreateDto;
import com.jepria.springstarter.feature.dto.FeatureDto;
import com.jepria.springstarter.feature.dto.FeatureStatusDto;
import com.jepria.springstarter.feature.dto.FeatureUpdateDto;
import com.jepria.springstarter.feature.service.FeatureService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FeatureController.class)
public class FeatureControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private FeatureService service;

  @Test
  public void getFeatureTest() throws Exception {

    when(service.get(1)).thenReturn(new FeatureDto());

    mockMvc.perform(get("/feature/1")).andExpect(status().isOk());

  }

  @Test
  public void createFeatureTest() throws Exception {
    FeatureCreateDto dto =
        FeatureCreateDto.builder()
            .featureName("name")
            .featureNameEn("nameEn")
            .description("description")
            .build();

    when(service.create(dto)).thenReturn(10);

    mockMvc.perform(
        post("/feature")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isCreated());
  }

  @Test
  public void updateFeatureTest() throws Exception {
    int featureId = 10;
    FeatureUpdateDto dto = new FeatureUpdateDto();
    dto.setFeatureName("featureName");
    dto.setDescription("description");
    when(service.update(10, dto)).thenReturn(true);

    mockMvc.perform(put("/feature/" + featureId)
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isOk());
  }

  @Test
  public void deleteFeatureTest() throws Exception {
    mockMvc.perform(delete("/feature/10")).andExpect(status().isOk());
  }

  @Test
  public void getAllFeaturesTest() throws Exception {
    List<FeatureDto> dtos = new ArrayList<>();
    dtos.add(new FeatureDto());
    when(service.getAll()).thenReturn((dtos));

    mockMvc.perform(get("/feature")).andExpect(status().isOk());
  }

  @Test
  public void getFeatureStatusOptionsTest() throws Exception {
    List<FeatureStatusDto> featureStatusOptions = new ArrayList<>();
    featureStatusOptions.add(new FeatureStatusDto("new", "New"));
    featureStatusOptions.add(new FeatureStatusDto("assign", "Assign"));

    when(service.getStatusOptions()).thenReturn(featureStatusOptions);
    MvcResult result = mockMvc.perform(get("/feature/option/feature-status"))
        .andExpect(status().isOk()).andReturn();

    Gson gson = new Gson();

    Assertions.assertEquals(gson.toJson(featureStatusOptions), result.getResponse().getContentAsString());
  }

  @Test
  public void setFeatureStatusOptionsTest() throws Exception {
    List<FeatureStatusDto> featureStatusOptions = new ArrayList<>();
    featureStatusOptions.add(new FeatureStatusDto("new", "New"));
    featureStatusOptions.add(new FeatureStatusDto("assign", "Assign"));

    MvcResult result = mockMvc.perform(
        post("/feature/option/feature-status")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(featureStatusOptions)))
        .andExpect(status().isOk()).andReturn();
  }
}
