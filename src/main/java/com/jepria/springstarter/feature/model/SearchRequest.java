package com.jepria.springstarter.feature.model;

public class SearchRequest<T> {
  T template;
  ColumnSortConfiguration listSortConfiguration;

  public T getTemplate() {
    return template;
  }

  public void setTemplate(T template) {
    this.template = template;
  }

  public ColumnSortConfiguration getListSortConfiguration() {
    return listSortConfiguration;
  }

  public void setListSortConfiguration(ColumnSortConfiguration listSortConfiguration) {
    this.listSortConfiguration = listSortConfiguration;
  }
}
