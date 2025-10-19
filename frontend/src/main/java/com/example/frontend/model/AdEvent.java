package com.example.frontend.model;

import java.time.Instant;

public class AdEvent {
    private int id;
    private String eventType;
    private Instant timestamp;

    public AdEvent() {}

    public AdEvent(int id, String eventType, Instant timestamp) {
        this.id = id;
        this.eventType = eventType;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
