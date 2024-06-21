package com.rvpnp.product.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class DataService1RestController {

    @GetMapping("/items/123")
    public void getItemInfo() throws InterruptedException {
        Thread.sleep(3000);

        log.info("Calling getItemInfo in the data-service1 service.");
    }
}
