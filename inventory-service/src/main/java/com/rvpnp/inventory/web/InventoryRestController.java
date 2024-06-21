package com.rvpnp.inventory.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class InventoryRestController {

    private static final String DATASERVICE1_HOST = "http://localhost:9077";
    private static final String DATASERVICE2_HOST = "http://localhost:9067";

    private final RestTemplate restTemplate;

    @Autowired
	private Tracer tracer;

    @GetMapping("/products/12345")
    public void getProductsInfo() throws InterruptedException {
        log.info("Calling getProductsInfo in the inventory-service");

        Thread.sleep(1000);

        restTemplate.getForEntity(DATASERVICE1_HOST + "/items/123", String.class);
        log.info("Calling dataservice1 {} from the inventory-service", DATASERVICE1_HOST);

        restTemplate.getForEntity(DATASERVICE2_HOST + "/items/234", String.class);
        log.info("Calling dataservice2 {} from the inventory-service", DATASERVICE2_HOST);

       
    }
}
