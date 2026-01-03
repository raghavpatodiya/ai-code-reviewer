package com.raghav.aicodereviewer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.raghav.aicodereviewer.model.dto.GitHubWebhookPayload;
import com.raghav.aicodereviewer.service.GitHubService;

@RestController
@RequestMapping("/webhook/github")
public class WebhookController {

    private final GitHubService gitHubService;

    public WebhookController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @PostMapping("/pr")
    public ResponseEntity<String> handlePullRequest(@RequestBody GitHubWebhookPayload payload) {

        String action = payload.getAction();

        // Only process useful PR events
        if (!(action.equals("opened") ||
            action.equals("reopened") ||
            action.equals("synchronize"))) {

            System.out.println("Ignoring PR event: " + action);
            return ResponseEntity.ok("Ignored");
        }

        System.out.println("======= GITHUB PR EVENT RECEIVED =======");
        System.out.println("Action      : " + payload.getAction());
        System.out.println("PR Number   : " + payload.getPull_request().getNumber());
        System.out.println("Title       : " + payload.getPull_request().getTitle());
        System.out.println("Repo        : " + payload.getRepository().getName());
        System.out.println("Owner       : " + payload.getRepository().getOwner().getLogin());
        System.out.println("Author      : " + payload.getSender().getLogin());
        System.out.println("========================================");

        gitHubService.fetchPRDetails(
                payload.getRepository().getOwner().getLogin(),
                payload.getRepository().getName(),
                payload.getPull_request().getNumber()
        );

        return ResponseEntity.ok("Processed");
    }
}