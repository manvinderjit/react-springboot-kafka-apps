package com.example.frontend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.Map;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;

import com.example.frontend.model.AdEvent;

@Controller
public class ChartController {

    private final RestTemplate restTemplate;

    @Value("${backend.api.url}")
    private String backendUrl;

    public ChartController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @GetMapping("/chart")
    public String showChart(Model model) {
        ResponseEntity<AdEvent[]> response = restTemplate.getForEntity(backendUrl, AdEvent[].class);
        AdEvent[] events = response.getBody();

        Map<String, Long> eventCounts = Arrays.stream(events)
            .collect(Collectors.groupingBy(AdEvent::getEventType, Collectors.counting()));

        model.addAttribute("labels", eventCounts.keySet());
        model.addAttribute("data", eventCounts.values());

        return "chart"; // Will resolve to chart.html
    }
}
