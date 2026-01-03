package com.raghav.aicodereviewer.model.dto;

import lombok.Data;

@Data
public class GitHubWebhookPayload {

    private String action;

    private PullRequest pull_request;

    private Repository repository;

    private Sender sender;

    @Data
    public static class PullRequest {
        private int number;
        private String state;
        private String title;
        private String html_url;
    }

    @Data
    public static class Repository {
        private String name;
        private Owner owner;

        @Data
        public static class Owner {
            private String login;
        }
    }

    @Data
    public static class Sender {
        private String login;
    }
}