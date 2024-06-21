package com.rvpnp.customer.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.rvpnp.customer.web.KafkaMessageListener.EVENT_TOPIC;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class PublisherRestController {

    private static final String INVENTORY_SERVICE_HOST = "http://localhost:9088";

    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/publisher/12345/products")
    public ResponseEntity<String> getOrderDetail() {
        log.info("Calling webhook at inventory-service");

        kafkaTemplate.send(EVENT_TOPIC, "key1", "body1");
        log.info("Publish message into topic {}", EVENT_TOPIC);

        return ResponseEntity.ok("success");
    }
}
