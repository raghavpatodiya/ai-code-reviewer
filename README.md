# ai-code-reviewer

# AI Code Reviewer / PR Assistant

An intelligent automated Pull Request reviewer that integrates with GitHub and uses AI + static analysis to review code, detect bugs, suggest improvements, and automatically post review comments back to the PR.

---

## 🚀 Features
- Listens to GitHub Pull Request webhooks
- Reads changed files and PR description
- Performs AI-based smart review
- Detects bugs, smells, security risks, performance issues
- Generates actionable review comments
- Supports inline comments
- Optional scoring & approval decision
- Stores review history (optional DB)
- Production-grade Spring Boot backend

---

## 🏗️ Project Structure

```
ai-code-reviewer
│
├── pom.xml
├── README.md
├── .gitignore
├── Dockerfile
├── docker-compose.yml
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.raghav.aicodereviewer
│   │   │       ├── AiCodeReviewerApplication.java
│   │   │
│   │   │       ├── config
│   │   │       │   ├── OpenAIConfig.java
│   │   │       │   ├── GitHubConfig.java
│   │   │       │   └── SecurityConfig.java
│   │   │
│   │   │       ├── controller
│   │   │       │   ├── WebhookController.java
│   │   │       │   └── HealthController.java
│   │   │
│   │   │       ├── service
│   │   │       │   ├── WebhookProcessingService.java
│   │   │       │   ├── GitHubService.java
│   │   │       │   ├── AIReviewService.java
│   │   │       │   └── ReviewCommentService.java
│   │   │
│   │   │       ├── client
│   │   │       │   ├── GitHubClient.java
│   │   │       │   └── OpenAIClient.java
│   │   │
│   │   │       ├── model
│   │   │       │   ├── dto
│   │   │       │   │   ├── GitHubWebhookPayload.java
│   │   │       │   │   ├── PullRequestDetails.java
│   │   │       │   │   ├── FileDiff.java
│   │   │       │   │   └── AIReviewResult.java
│   │   │       │   ├── entity
│   │   │       │   │   ├── PullRequestRecord.java
│   │   │       │   │   └── ReviewLog.java
│   │   │       │   └── enums
│   │   │       │       ├── RiskLevel.java
│   │   │       │       └── ReviewStatus.java
│   │   │
│   │   │       ├── repository
│   │   │       │   ├── PullRequestRepository.java
│   │   │       │   └── ReviewLogRepository.java
│   │   │
│   │   │       ├── util
│   │   │       │   ├── GitHubSignatureValidator.java
│   │   │       │   ├── DiffParser.java
│   │   │       │   └── PromptBuilder.java
│   │   │
│   │   │       └── exception
│   │   │           ├── GlobalExceptionHandler.java
│   │   │           ├── GitHubApiException.java
│   │   │           └── AIServiceException.java
│   │   │
│   │   └── resources
│   │       ├── application.yml
│   │       ├── logback.xml
│   │       └── prompts
│   │           ├── review-prompt.txt
│   │           └── inline-comment-prompt.txt
│   │
│   └── test
│       └── com.raghav.aicodereviewer
│           ├── WebhookControllerTest.java
│           ├── GitHubServiceTest.java
│           └── AIReviewServiceTest.java
```

---

## 🧰 Tech Stack
- Java 17+
- Spring Boot
- OpenAI / LLM API
- GitHub REST APIs
- JPA + DB (optional)
- Docker (optional)

---

## ⚙️ High Level Flow
1. GitHub PR triggers webhook  
2. Service receives payload  
3. Fetch PR files & diffs  
4. Analyze using AI + rules  
5. Post review comments  

---

## 📌 Status
Work in Progress 🚧