package com.example.frontend.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }    

    public void sendEvent(String eventType) {
        String topic = switch (eventType) {
            case "ad_viewed" -> "ad_viewed";
            case "ad_clicked" -> "ad_clicked";
            case "ad_closed" -> "ad_closed";
            default -> throw new IllegalArgumentException("Unknown event type: " + eventType);
        };

        String eventJson = String.format(
                "{\"eventType\":\"%s\", \"timestamp\":\"%s\"}",
                eventType,
                Instant.now().toString()
        );

        kafkaTemplate.send(topic, eventJson);
        System.out.println("Sent event to topic [" + topic + "]: " + eventJson);
    }
}
