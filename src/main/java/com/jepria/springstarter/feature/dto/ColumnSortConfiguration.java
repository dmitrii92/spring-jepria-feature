package com.jepria.springstarter.feature.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColumnSortConfiguration {
  String columnName;
  String sortOrder;
}
