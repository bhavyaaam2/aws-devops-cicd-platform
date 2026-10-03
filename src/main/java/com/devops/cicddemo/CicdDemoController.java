package com.devops.cicddemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CicdDemoController {

    @GetMapping("/")
    public String home() {
        return "AWS DevOps CI/CD Platform is running!";
    }

    @GetMapping("/health")
    public String health() {
        return "Application is healthy";
    }
}
