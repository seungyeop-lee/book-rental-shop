package com.github.seungyeop_lee.book_rental_shop.backoffice.healthcheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
