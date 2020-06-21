package com.jepria.springstarter.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("title", "Home Page");
    model.addAttribute("name", "Dmitrii");
    return "home";
  }
}
