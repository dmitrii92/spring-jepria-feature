package com.jepria.springstarter.feature.controller;

import com.jepria.springstarter.feature.model.CounterContent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CounterController {
  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/counter")
  public CounterContent greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    return new CounterContent(counter.incrementAndGet(), String.format(template, name));
  }
}
