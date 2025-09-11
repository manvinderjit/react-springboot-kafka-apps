package com.example.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.Map;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;

// Replace with your model class location
import com.example.frontend.model.AdEvent;


// ChartController.java
@Controller
public class ChartController {

    private final RestTemplate restTemplate;

    public ChartController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @GetMapping("/chart")
    public String showChart(Model model) {
        String backendUrl = "http://localhost:8080/api/events"; // Adjust port if needed
        ResponseEntity<AdEvent[]> response = restTemplate.getForEntity(backendUrl, AdEvent[].class);
        AdEvent[] events = response.getBody();

        // Aggregate counts per event type (e.g., CLICKED, VIEWED, CLOSED)
        Map<String, Long> eventCounts = Arrays.stream(events)
            .collect(Collectors.groupingBy(AdEvent::getEventType, Collectors.counting()));

        model.addAttribute("labels", eventCounts.keySet());
        model.addAttribute("data", eventCounts.values());

        return "chart"; // chart.html
    }
}
