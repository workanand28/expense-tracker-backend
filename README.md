# Expense Tracker Backend API

A robust, production-ready RESTful API built with **Spring Boot** and **MongoDB** for logging, tracking, filtering, and managing personal expenses.

---

## Features

- **CRUD Operations**: Create, read, and retrieve individual expenses.
- **Pagination & Sorting**: Efficient pagination for expense lists via Spring Data `Pageable`.
- **Dynamic Search & Filtering**: Multi-criteria search powered by Spring Data MongoDB `MongoTemplate` and custom repository implementation:
  - Filter by category
  - Filter by minimum and maximum amount range
  - Case-insensitive regex keyword search on descriptions
- **Data Validation**: Request payload validation using Jakarta Bean Validation (`@NotNull`, `@NotBlank`, `@Positive`, etc.).
- **Centralized Exception Handling**: Global exception handler providing structured JSON error responses for `404 Not Found`, validation errors (`400 Bad Request`), and server errors.
- **Unit & Integration Tests**: Unit and slice tests with JUnit 5, Mockito, and MockMvc.

---

## Tech Stack

| Technology | Purpose |
|---|---|
| **Java 26** | Programming Language |
| **Spring Boot 4.x** | Core Framework |
| **Spring Data MongoDB** | MongoDB Object-Document Mapping & Querying |
| **MongoDB** | NoSQL Database |
| **Jakarta Validation** | Request Body Validation |
| **Maven** | Dependency Management & Build Tool |
| **JUnit 5 & Mockito** | Testing Frameworks |

---

## Project Structure

```
src/
├── main/
│   ├── java/com/example/expense_tracker/
│   │   ├── ExpenseTrackerApplication.java
│   │   ├── config/               # Configuration classes
│   │   ├── controller/           # REST API Controllers (ExpenseController)
│   │   ├── dto/                  # Request & Response DTOs
│   │   ├── entity/               # MongoDB Document Entities (Expense)
│   │   ├── exception/            # Custom Exceptions & GlobalExceptionHandler
│   │   ├── repository/           # MongoRepository & Custom Repository Impl
│   │   └── service/              # Service Interfaces & Implementations
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/example/expense_tracker/
        ├── ExpenseTrackerApplicationTests.java
        ├── controller/ExpenseControllerTest.java
        └── service/ExpenseServiceTest.java
```

---

## API Reference

Base URLs: `/api/expenses` or `/api/expense`

### 1. Create Expense
- **Method:** `POST`
- **Path:** `/api/expenses`
- **Request Body:**
```json
{
  "amount": 45.50,
  "category": "Food",
  "description": "Lunch at cafe"
}
```
- **Response:** `201 Created`
```json
{
  "id": "65f2a1b2c3d4e5f6a7b8c9d0",
  "amount": 45.50,
  "category": "Food",
  "description": "Lunch at cafe",
  "date": "2026-09-11"
}
```

---

### 2. Get Expense by ID
- **Method:** `GET`
- **Path:** `/api/expenses/{id}`
- **Response:** `200 OK`
```json
{
  "id": "65f2a1b2c3d4e5f6a7b8c9d0",
  "amount": 45.50,
  "category": "Food",
  "description": "Lunch at cafe",
  "date": "2026-09-11"
}
```
- **Error Response (Not Found):** `404 Not Found`
```json
{
  "timestamp": "2026-09-11T01:20:00",
  "status": 404,
  "error": "Not Found",
  "message": "Expense with id: 65f2a1b2c3d4e5f6a7b8c9d0 not found!"
}
```

---

### 3. Get All Expenses (Paginated)
- **Method:** `GET`
- **Path:** `/api/expenses`
- **Query Parameters:**
  - `page` (optional, default: `0`)
  - `size` (optional, default: `20`)
  - `sort` (optional, e.g., `date,desc` or `amount,asc`)
- **Response:** `200 OK` (Spring Data `Page` JSON structure)

---

### 4. Get Expenses by Category
- **Method:** `GET`
- **Path:** `/api/expenses/category/{category}`
- **Response:** `200 OK` (Array of expenses matching category)

---

### 5. Search & Filter Expenses
- **Method:** `GET`
- **Path:** `/api/expenses/search`
- **Query Parameters:**
  - `category` (optional): Filter by exact category
  - `minAmount` (optional): Minimum amount threshold
  - `maxAmount` (optional): Maximum amount threshold
  - `search` (optional): Case-insensitive keyword search on `description`
  - `page`, `size`, `sort` (optional): Standard pagination parameters
- **Response:** `200 OK` (Paginated list of matched expenses)
- **Example Request:**
  `GET /api/expenses/search?category=Food&minAmount=10&maxAmount=100&search=lunch&page=0&size=10`

---

## Getting Started

### Prerequisites
- **Java 21+** (Java 26 recommended)
- **MongoDB** running locally on default port `27017` (or configured via connection URI)
- **Git**

### Installation & Running Locally

1. **Clone the repository:**
   ```bash
   git clone https://github.com/workanand28/expense-tracker-backend.git
   cd expense-tracker-backend
   ```

2. **Configure MongoDB (optional):**
   By default, Spring Boot connects to `mongodb://localhost:27017/test`. You can customize this in `src/main/resources/application.properties`:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/expense_tracker
   ```

3. **Build the project:**
   ```bash
   ./mvnw clean compile
   ```

4. **Run tests:**
   ```bash
   ./mvnw test
   ```

5. **Start the application:**
   ```bash
   ./mvnw spring-boot:run
   ```
   The application will start on `http://localhost:8080`.

---

## Running Tests

Execute the unit and integration test suite:

```bash
# On Linux/macOS
./mvnw test

# On Windows PowerShell / Command Prompt
.\mvnw test
```
