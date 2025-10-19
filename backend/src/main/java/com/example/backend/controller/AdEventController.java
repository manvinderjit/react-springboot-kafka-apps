package com.example.backend.controller;

import com.example.backend.entity.AdEvent;
import com.example.backend.repository.AdEventRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AdEventController {

    private final AdEventRepository repository;

    public AdEventController(AdEventRepository repository) {
        this.repository = repository;
    }

    // Return all events (for testing or detailed view)
    @GetMapping("/api/events")
    public List<AdEvent> getAllEvents() {
        return repository.findAll();
    }

    // Return counts of events by type (for charts)
    @GetMapping("/api/events/counts")
    public Map<String, Long> getEventCounts() {
        return repository.findAll()
                .stream()
                .collect(Collectors.groupingBy(AdEvent::getEventType, Collectors.counting()));
    }
}
