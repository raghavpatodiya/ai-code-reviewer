package com.raghav.aicodereviewer.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import io.github.cdimascio.dotenv.Dotenv;

@Service
public class GitHubService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String token = Dotenv.load().get("GITHUB_TOKEN");

    public void fetchPRDetails(String owner, String repo, int prNumber) {

        if (token == null) {
            System.out.println("❌ ERROR: GITHUB_TOKEN is not set in environment");
            return;
        }

        System.out.println("Calling GitHub API...");

        String url = String.format(
                "https://api.github.com/repos/%s/%s/pulls/%d",
                owner, repo, prNumber
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Accept", "application/vnd.github+json");

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response =
                    restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, entity, String.class);

            System.out.println("======= GITHUB API RESPONSE =======");
            System.out.println(response.getBody());
            System.out.println("===================================");

        } catch (Exception e) {
            System.out.println("GitHub API Error: " + e.getMessage());
        }
    }
    /**
     * Placeholder method for future implementation.
     * This will later call:
     * GET /repos/{owner}/{repo}/pulls/{number}/files
     * to fetch changed files and diff patches.
     */
    public void fetchChangedFiles(String owner, String repo, int prNumber) {
        logSection("Fetching Changed Files Placeholder");
        System.out.println(">>> Placeholder: Fetching changed files for PR " + prNumber);
        System.out.println(">>> Future Scope: Will call /pulls/{number}/files API here.");
    }

    /**
     * Utility formatter for cleaner logs
     */
    private void logSection(String title) {
        System.out.println("\n========== " + title + " ==========\n");
    }
}