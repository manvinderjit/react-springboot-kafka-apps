package com.example.frontend.controller;

import com.example.frontend.service.ProducerService;
import com.example.frontend.service.AdService;
import com.example.frontend.model.Ad;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FrontendController {

    private final ProducerService producerService;
    private final AdService adService;

    public FrontendController(ProducerService producerService, AdService adService) {
        this.producerService = producerService;
        this.adService = adService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Ad> ads = adService.getAllAds();
        model.addAttribute("ads", ads);
        return "index";
    }

    @PostMapping("/send-event")
    public String sendEvent(@RequestParam("eventType") String eventType, Model model) {
        producerService.sendEvent(eventType);
        model.addAttribute("message", "Sent event: " + eventType);
        List<Ad> ads = adService.getAllAds();
        model.addAttribute("ads", ads);
        return "index";
    }

    @PostMapping("/ad-event")
    public String sendAdEvent(@RequestParam("eventType") String eventType, 
                             @RequestParam("adId") Long adId,
                             @RequestParam("adTitle") String adTitle,
                             Model model) {
        producerService.sendEvent(eventType, adId, adTitle);
        model.addAttribute("message", "Ad " + eventType.replace("ad_", "") + ": " + adTitle);
        List<Ad> ads = adService.getAllAds();
        model.addAttribute("ads", ads);
        return "index";
    }

    @GetMapping("/ad/{id}")
    public String viewAd(@PathVariable Long id, Model model) {
        Ad ad = adService.getAdById(id);
        // Send ad viewed event
        producerService.sendEvent("ad_viewed", ad.getId(), ad.getTitle());
        model.addAttribute("ad", ad);
        return "ad-detail";
    }
}
