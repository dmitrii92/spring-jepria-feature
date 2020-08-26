package com.jepria.springstarter.feature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jepria.springstarter.feature.dto.FeatureCreateDto;
import com.jepria.springstarter.feature.dto.FeatureDto;
import com.jepria.springstarter.feature.service.FeatureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
}
