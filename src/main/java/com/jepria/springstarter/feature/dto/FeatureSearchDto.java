package com.jepria.springstarter.feature.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class FeatureSearchDto {
  private Integer featureId;
  private String featureNameTemplate;
  private String featureNameEnTemplate;
  private List<String> statusCodeList;
  private Integer authorId;
  private Integer responsibleId;
  private Date dateInsFrom;
  private Date dateInsTo;
  private Integer maxRowCount;
}
