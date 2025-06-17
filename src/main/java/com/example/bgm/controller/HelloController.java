package com.example.bgm.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/public/hello")
  public String hello() {
    return "hello world!";
  }

  @SecurityRequirement(name = "firebase")
  @GetMapping("/private/hello")
  public String privateHello() {
    return "hello user";
  }
}
