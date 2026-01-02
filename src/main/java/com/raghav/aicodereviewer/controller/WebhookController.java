package com.raghav.aicodereviewer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook/github")
public class WebhookController {

    @PostMapping("/pr")
    public ResponseEntity<String> handlePullRequest(@RequestBody String payload) {
        System.out.println("Received PR Webhook: " + payload);
        return ResponseEntity.ok("Webhook received");
    }
}