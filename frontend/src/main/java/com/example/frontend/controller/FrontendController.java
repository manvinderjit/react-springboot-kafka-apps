package com.example.frontend.controller;

import com.example.frontend.service.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FrontendController {

    private final ProducerService producerService;

    public FrontendController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/send-event")
    public String sendEvent(@RequestParam("eventType") String eventType, Model model) {
        producerService.sendEvent(eventType);
        model.addAttribute("message", "Sent event: " + eventType);
        return "index";
    }
}
