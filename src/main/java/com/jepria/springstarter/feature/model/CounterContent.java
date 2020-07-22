package com.jepria.springstarter.feature.model;

public class CounterContent {
  private final long id;
  private final String content;

  public CounterContent(long id, String content) {
    this.id = id;
    this.content = content;
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }
}
