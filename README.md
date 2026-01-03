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

## 🧰 Tech Stack
- Java 21+
- Spring Boot
- Gradle
- OpenAI / LLM API
- GitHub REST APIs
- JPA + DB (optional)
- Docker (optional)

---

## 🏗️ Project Structure

```
ai-code-reviewer
│
├── build.gradle
├── gradlew
├── gradlew.bat
├── settings.gradle
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

## ▶️ Build & Run

### Build
```
./gradlew clean build
```

### Build Without Tests
```
./gradlew clean build -x test
```

### Run App
```
./gradlew bootRun
```

### Run Tests
```
./gradlew test
```

---

## ✅ Setup & Execution (Real GitHub Webhook Flow)

### 1️⃣ Prerequisites
- Java 21+
- Gradle installed
- GitHub account & repository
- Internet connection (for ngrok tunnel)

---

### 2️⃣ Configure GitHub Token

Create a **GitHub Personal Access Token (Classic)**

1. Go to: https://github.com/settings/tokens  
2. Click: **Generate new token → Classic**
3. Name: `ai-code-reviewer`
4. Expiry: 90 days (recommended)
5. Scopes → enable:
```
repo
```
6. Generate and copy the token.

---

### 3️⃣ Store Token Securely Using `.env`

Create `.env` in project root (same level as `build.gradle`)
```
GITHUB_TOKEN=ghp_your_token_here
```

Ensure `.env` is in `.gitignore`.

This project uses **spring-dotenv**, so the variable is automatically available.

---

### 4️⃣ Run Application
```
./gradlew bootRun
```

Your app now runs at:
```
http://localhost:8080
```

---

### 5️⃣ Expose Localhost Using ngrok

Install ngrok (first time only):
```
brew install ngrok
```

Login (one time):
```
ngrok config add-authtoken <your-ngrok-token>
```

Start tunnel:
```
ngrok http 8080
```

Copy the HTTPS forwarding URL, e.g.
```
https://abc123.ngrok-free.app
```

---

### 6️⃣ Create GitHub Webhook

Go to:
```
Repo → Settings → Webhooks → Add Webhook
```

Fill:
```
Payload URL: https://abc123.ngrok-free.app/webhook/github/pr
Content type: application/json
Secret: (leave empty for now)
```

Select events:
```
Let me select individual events
✔ Pull requests
```

Save webhook.

---

### 7️⃣ Trigger Real Test

Do ANY of:
- Create a Pull Request
- Push commits to existing PR
- Reopen PR

---

### 🎯 Expected Console Output

Your Spring Boot terminal should show:

```
======= GITHUB PR EVENT RECEIVED =======
Action      : opened / synchronize / reopened
PR Number   : X
Repo        : your-repo
Owner       : you
Author      : you
========================================
Calling GitHub API...
======= GITHUB API RESPONSE =======
<real GitHub JSON here>
===================================
```

Congratulations 🎉  
You now have a live PR webhook integration working!

---

## ⚙️ High Level Flow
1️⃣ GitHub PR triggers webhook  
2️⃣ Service receives payload  
3️⃣ Fetch PR files & diffs  
4️⃣ Analyze using AI + rules  
5️⃣ Post review comments  

---

## 📌 Status
Work in Progress 🚧
