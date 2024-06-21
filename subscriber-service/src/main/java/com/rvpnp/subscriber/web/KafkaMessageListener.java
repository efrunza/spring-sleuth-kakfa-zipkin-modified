package com.rvpnp.customer.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaMessageListener {

    public static final String EVENT_TOPIC = "event-topic";

    private static final String INVENTORY_SERVICE_HOST = "http://localhost:9088";

    private final RestTemplate restTemplate;

    @KafkaListener(topics = EVENT_TOPIC)
    public void receiveMessage(String message) {
        log.info("Receive message from topic {} with message {}", EVENT_TOPIC, message);

        restTemplate.getForEntity(INVENTORY_SERVICE_HOST + "/products/12345", String.class);
        log.info("Call INVENTORY_SERVICE_HOST {} from KafkaMessageListener", INVENTORY_SERVICE_HOST);
    }
}
