# Online Judge Platform

A LeetCode/HackerRank-inspired Online Judge Platform built using Spring Boot, PostgreSQL, JWT Authentication, and Java-based Code Execution Engine.

The platform allows users to solve coding problems online, submit solutions, and get automatic evaluation against predefined test cases.

---

## Features

### Authentication & Authorization

- User Registration
- User Login
- JWT Authentication
- Protected APIs
- Role-based architecture (User/Admin ready)

### Problem Management

- Create Coding Problems
- Store Problem Statements
- Difficulty Levels (Easy, Medium, Hard)
- Problem Constraints

### Test Case Management

- Add Test Cases
- Hidden Test Cases
- Multiple Test Cases per Problem

### Submission Management

- Submit Solutions
- View Submission History
- View Submission Details

### Online Judge

- Java Code Compilation
- Java Code Execution
- Input Handling
- Output Capture
- Automatic Evaluation

Supported Verdicts:

- ACCEPTED
- WRONG_ANSWER
- COMPILATION_ERROR
- RUNTIME_ERROR

---

## Tech Stack

### Backend

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Hibernate

### Database

- PostgreSQL

### Authentication

- JWT (JSON Web Tokens)

### Build Tool

- Maven

### Future Enhancements

- Docker Sandbox
- Contest Module
- Leaderboard
- C++ Support
- Python Support
- Rate Limiting
- Admin Dashboard

---

## Project Architecture

```
Client
   │
   ▼
Spring Boot API
   │
   ├── Authentication Module
   ├── Problem Module
   ├── Test Case Module
   ├── Submission Module
   └── Judge Module
           │
           ▼
     Java Executor
           │
           ▼
      javac / java
           │
           ▼
      Judge Result
```

---

## Database Schema

### Users

| Column | Type |
|----------|----------|
| id | BIGINT |
| username | VARCHAR |
| email | VARCHAR |
| password | VARCHAR |
| role | VARCHAR |

### Problems

| Column | Type |
|----------|----------|
| id | BIGINT |
| title | VARCHAR |
| description | TEXT |
| difficulty | VARCHAR |
| constraints_text | TEXT |
| created_at | TIMESTAMP |
| updated_at | TIMESTAMP |

### Test Cases

| Column | Type |
|----------|----------|
| id | BIGINT |
| problem_id | BIGINT |
| input_data | TEXT |
| expected_output | TEXT |
| hidden | BOOLEAN |

### Submissions

| Column | Type |
|----------|----------|
| id | BIGINT |
| problem_id | BIGINT |
| user_id | BIGINT |
| source_code | TEXT |
| language | VARCHAR |
| status | VARCHAR |
| passed_test_cases | INTEGER |
| total_test_cases | INTEGER |
| submitted_at | TIMESTAMP |

---

## Project Structure

```text
src/main/java/com/himanshukori/onlinejudge

├── auth
│
├── security
│
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

## API Endpoints

### Authentication

#### Register

```http
POST /api/auth/register
```

#### Login

```http
POST /api/auth/login
```

---

### User

#### Current User

```http
GET /api/users/me
```

---

### Problems

#### Create Problem

```http
POST /api/problems
```

---

### Test Cases

#### Add Test Case

```http
POST /api/testcases
```

---

### Submissions

#### Submit Solution

```http
POST /api/submissions
```

#### My Submissions

```http
GET /api/submissions/my
```

#### Submission Details

```http
GET /api/submissions/{id}
```

---

## Running the Project

### Clone Repository

```bash
git clone <repository-url>
```

### Navigate

```bash
cd online-judge/backend
```

### Configure PostgreSQL

Create database:

```sql
CREATE DATABASE online_judge;
```

Update:

```yaml
src/main/resources/application.yml
```

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/online_judge
    username: postgres
    password: root
```

---

### Run Application

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

---

## Judge Workflow

```text
User Submission
        │
        ▼
Submission Service
        │
        ▼
Judge Service
        │
        ▼
Load Test Cases
        │
        ▼
Execute Code
        │
        ▼
Capture Output
        │
        ▼
Compare Output
        │
        ▼
Generate Verdict
```

---

## Sample Verdict Flow

### Accepted

Expected Output:

```text
hello
```

Actual Output:

```text
hello
```

Verdict:

```text
ACCEPTED
```

### Wrong Answer

Expected Output:

```text
hello
```

Actual Output:

```text
world
```

Verdict:

```text
WRONG_ANSWER
```

### Compilation Error

```java
System.out.println("hello")
```

Verdict:

```text
COMPILATION_ERROR
```

### Runtime Error

```java
int x = 10 / 0;
```

Verdict:

```text
RUNTIME_ERROR
```

---

## Future Roadmap

### Phase 1 (Completed)

- Authentication
- Problems
- Test Cases
- Submissions
- Judge Module

### Phase 2

- Docker Sandbox
- Time Limits
- Memory Limits
- Process Isolation

### Phase 3

- Contest Module
- Leaderboards
- User Rankings

### Phase 4

- C++ Execution
- Python Execution
- Async Judging

---

## Learning Outcomes

This project demonstrates:

- Spring Boot Backend Development
- REST API Design
- JWT Authentication
- Database Design
- JPA Relationships
- Code Execution Engines
- Judge System Design
- Software Architecture
- Secure Execution Concepts

---

## Author

**Himanshu Kori**

M.Tech Student | Backend Development | System Design | Java & Spring Boot