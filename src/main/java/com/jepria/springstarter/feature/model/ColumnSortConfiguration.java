package com.jepria.springstarter.feature.model;

public class ColumnSortConfiguration {
  String columnName;
  String sortOrder;

  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public String getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(String sortOrder) {
    this.sortOrder = sortOrder;
  }
}
