package com.rvpnp.product.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class DataService2RestController {

    @Autowired
	private Tracer tracer;

    @GetMapping("/items/234")
    public void getItemInfo() throws InterruptedException {
        Thread.sleep(3000);

        log.info("Calling getItemInfo in the data-service2 service.");

        Span dbSpan = this.tracer.nextSpan().name("Database Operation 1 - Starts");
		try (Tracer.SpanInScope ws = this.tracer.withSpan(dbSpan.start())) {
			
			dbSpan.tag("calling", "MongoDB");

            Random r = new Random();
            int multiplier = r.nextInt(6) * 1000;
            System.out.println("multiplier: " + multiplier);
            Thread.sleep(multiplier);

			dbSpan.event("Database Operation 1 - Ends");
		}
		finally {
			dbSpan.end();
		}
    }
}
