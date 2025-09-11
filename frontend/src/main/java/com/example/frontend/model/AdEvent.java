package com.example.frontend.model;

public class AdEvent {
    private String adId;
    private String eventType;

    public AdEvent() {}

    public AdEvent(String adId, String eventType) {
        this.adId = adId;
        this.eventType = eventType;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
