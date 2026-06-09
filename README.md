# Online Judge Platform

A LeetCode/HackerRank-inspired Online Judge Platform built using Java 21, Spring Boot, PostgreSQL, JWT Authentication, and a Docker-based Code Execution Engine.

The platform allows users to solve coding problems online, submit solutions, and get automatic evaluation against predefined test cases inside isolated Docker containers.

---

# Features

## Authentication & Authorization

* User Registration
* User Login
* JWT Authentication
* Spring Security Integration
* Protected APIs
* Role-based Architecture (User/Admin Ready)

---

## Problem Management

* Create Coding Problems
* Store Problem Statements
* Difficulty Levels (Easy, Medium, Hard)
* Constraints Support
* Multiple Test Cases per Problem

---

## Test Case Management

* Public Test Cases
* Hidden Test Cases
* Multiple Test Cases per Problem
* Automatic Evaluation Support

---

## Submission Management

* Submit Solutions
* Store Submission History
* Track Verdicts
* Track Passed Test Cases
* Track Total Test Cases

---

## Online Judge

### Supported Verdicts

* ACCEPTED
* WRONG_ANSWER
* COMPILATION_ERROR
* RUNTIME_ERROR
* TIME_LIMIT_EXCEEDED

### Judge Workflow

```text
Submission
     │
     ▼
Judge Service
     │
     ▼
Executor Factory
     │
     ▼
Docker Java Executor
     │
     ▼
Docker Container
     │
 ┌───┴────┐
 ▼        ▼
Compile   Run
 │         │
 ▼         ▼
Result Evaluation
 │
 ▼
Verdict
```

---

## Docker Sandbox

Implemented Features:

* Docker-based Java execution
* Workspace isolation
* Separate compile container
* Separate run container
* Automatic workspace cleanup
* Automatic container cleanup

---

## Resource Isolation

### Compile Container

* Memory Limit: 256 MB
* CPU Limit: 1 Core

### Run Container

* Memory Limit: 256 MB
* CPU Limit: 1 Core

---

## Security Features

* Time Limit Protection
* Infinite Loop Protection
* Memory Isolation
* CPU Isolation
* Workspace Cleanup
* Container Cleanup

---

# Tech Stack

## Backend

* Java 21
* Spring Boot 3
* Spring Security
* Spring Data JPA
* Hibernate
* Maven

## Database

* PostgreSQL

## Authentication

* JWT (JSON Web Tokens)

## Code Execution

* Docker
* Docker Java Executor

## Deployment (Planned)

* Docker Compose
* Nginx
* Oracle Cloud Free Tier

## Version Control

* Git
* GitHub

---

# Project Architecture

```text
Client
   │
   ▼
Spring Boot API
   │
   ├── Authentication Module
   ├── User Module
   ├── Problem Module
   ├── Test Case Module
   ├── Submission Module
   └── Judge Module
           │
           ▼
     Executor Factory
           │
           ▼
   Docker Java Executor
           │
           ▼
     Docker Container
           │
      ┌────┴────┐
      ▼         ▼
   Compile      Run
      │         │
      ▼         ▼
   Judge Result
```

---

# Database Schema

## Users

| Column   | Type    |
| -------- | ------- |
| id       | BIGINT  |
| username | VARCHAR |
| email    | VARCHAR |
| password | VARCHAR |
| role     | VARCHAR |

---

## Problems

| Column           | Type      |
| ---------------- | --------- |
| id               | BIGINT    |
| title            | VARCHAR   |
| description      | TEXT      |
| difficulty       | VARCHAR   |
| constraints_text | TEXT      |
| created_at       | TIMESTAMP |
| updated_at       | TIMESTAMP |

---

## Test Cases

| Column          | Type    |
| --------------- | ------- |
| id              | BIGINT  |
| problem_id      | BIGINT  |
| input_data      | TEXT    |
| expected_output | TEXT    |
| hidden          | BOOLEAN |

---

## Submissions

| Column            | Type      |
| ----------------- | --------- |
| id                | BIGINT    |
| problem_id        | BIGINT    |
| user_id           | BIGINT    |
| source_code       | TEXT      |
| language          | VARCHAR   |
| verdict           | VARCHAR   |
| passed_test_cases | INTEGER   |
| total_test_cases  | INTEGER   |
| submitted_at      | TIMESTAMP |

---

# Project Structure

```text
src/main/java/com/himanshukori/onlinejudge

├── auth
├── security
├── user
│   ├── controller
│   ├── service
│   ├── repository
│   └── entity
│
├── problem
│   ├── controller
│   ├── service
│   ├── repository
│   ├── dto
│   └── entity
│
├── testcase
│   ├── controller
│   ├── service
│   ├── repository
│   ├── dto
│   └── entity
│
├── submission
│   ├── controller
│   ├── service
│   ├── repository
│   ├── dto
│   └── entity
│
└── judge
    ├── dto
    ├── executor
    └── service
```

---

# API Endpoints

## Authentication

### Register

```http
POST /api/auth/register
```

### Login

```http
POST /api/auth/login
```

---

## User

### Current User

```http
GET /api/users/me
```

---

## Problems

### Create Problem

```http
POST /api/problems
```

### Get All Problems

```http
GET /api/problems
```

### Get Problem By ID

```http
GET /api/problems/{id}
```

---

## Test Cases

### Add Test Case

```http
POST /api/testcases
```

---

## Submissions

### Submit Solution

```http
POST /api/submissions
```

### My Submissions

```http
GET /api/submissions/my
```

### Submission Details

```http
GET /api/submissions/{id}
```

---

# Running the Project

## Clone Repository

```bash
git clone <repository-url>
```

## Configure PostgreSQL

```sql
CREATE DATABASE online_judge;
```

Update:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/online_judge
    username: postgres
    password: your_password
```

---

## Build Project

```bash
mvn clean install
```

---

## Run Application

```bash
mvn spring-boot:run
```

---

# Current Status

## Completed

* JWT Authentication
* Spring Security
* Problem Management
* Test Case Management
* Submission Management
* Judge Service
* Docker-based Java Execution
* Resource Isolation
* Time Limit Enforcement
* Memory Limits
* CPU Limits
* Workspace Cleanup
* Container Cleanup

---

# Planned Features

## Language Support

* Python Execution
* C++ Execution

## Contest System

* Contest Creation
* Contest Registration
* Contest Problems

## Leaderboards

* Ranking System
* Solved Count
* Penalty Calculation

## Deployment

* Docker Compose
* Nginx Reverse Proxy
* Oracle Cloud Deployment

---

# Learning Outcomes

This project demonstrates:

* Spring Boot Backend Development
* JWT Authentication
* REST API Design
* Database Design
* JPA Relationships
* Docker Sandboxing
* Secure Code Execution
* Resource Isolation
* Judge System Design
* Software Architecture
* Backend System Design

---

# Author

**Himanshu Kori**

M.Tech Student | Java Backend Developer | Spring Boot Enthusiast
