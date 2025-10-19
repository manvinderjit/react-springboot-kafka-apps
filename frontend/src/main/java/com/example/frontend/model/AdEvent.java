package com.example.frontend.model;

import java.time.Instant;

public class AdEvent {
    private Long id;
    private String eventType;
    private Instant timestamp;
    private Long adId;
    private String adTitle;

    public AdEvent() {}

    public AdEvent(Long id, String eventType, Instant timestamp, Long adId, String adTitle) {
        this.id = id;
        this.eventType = eventType;
        this.timestamp = timestamp;
        this.adId = adId;
        this.adTitle = adTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }
}
