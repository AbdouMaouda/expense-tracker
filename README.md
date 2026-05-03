# BudgetFlow
A personal finance REST API built with Spring Boot enabling expense tracking, transaction categorization, and budget management with persistent storage in PostgreSQL.
---
## Features
- **Expense Tracking** — Create, read, update, and delete personal expenses with full validation
- **Transaction Categorization** — Organize expenses by category for spending pattern analysis
- **Budget Management** — Track and aggregate spending across categories and time periods
- **JWT Authentication** — Each user accesses only their own financial records
- **User-level Data Isolation** — Spring Security ensures complete separation between user accounts
- **Optimized Queries** — Indexed JPA/Hibernate schemas supporting efficient aggregation for financial reporting
- **Category and Month Filters** — Filter expenses by category or month for targeted analysis
---
## Tech Stack
| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot |
| Security | Spring Security, JWT |
| ORM | JPA / Hibernate |
| Database (Dev) | H2 (in-memory) |
| Database (Prod) | PostgreSQL |
| Build Tool | Maven |
---
## Local Setup
### Prerequisites
- Java 21+
- Maven
- PostgreSQL (for production) or H2 (runs automatically in dev)
### 1. Clone the repository
git clone https://github.com/AbdouMaouda/expense-tracker.git
cd expense-tracker
### 2. Set up environment variables
Create or update src/main/resources/application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/budgetflow
spring.datasource.username=your_pg_username
spring.datasource.password=your_pg_password
jwt.secret=your_jwt_secret
jwt.expiration=86400000
### 3. Run the app
./mvnw spring-boot:run
The API will be available at http://localhost:8080
---
## Environment Variables
| Variable | Description |
|---|---|
| spring.datasource.url | PostgreSQL JDBC connection URL |
| spring.datasource.username | Database username |
| spring.datasource.password | Database password |
| jwt.secret | Secret key used to sign JWT tokens |
| jwt.expiration | Token expiration time in milliseconds |
---
## API Endpoints
### Auth
- POST /api/auth/register — Register a new user
- POST /api/auth/login — Login and receive a JWT token
### Expenses
- GET /api/expenses — Get all expenses for the logged-in user
- GET /api/expenses/{id} — Get a specific expense
- GET /api/expenses?category={category} — Filter expenses by category
- GET /api/expenses?month={month} — Filter expenses by month
- POST /api/expenses — Create a new expense
- PUT /api/expenses/{id} — Update an existing expense
- DELETE /api/expenses/{id} — Delete an expense
---
## Project Structure
```
src/
├── main/java/com/example/demo/
│   ├── Dto/                — Request and response DTOs
│   ├── configuration/      — Security config, beans
│   ├── controller/         — REST controllers
│   ├── model/              — JPA entities
│   ├── repository/         — Spring Data JPA repositories
│   ├── security/           — JWT filter, auth entry point
│   ├── service/            — Business logic
│   ├── utils/              — Utility classes
│   └── DemoApplication.java — Main entry point
├── resources/              — application.properties
└── test/java/com/example/demo/ — Unit tests
```
---
## License
This project is licensed under the MIT License.
---
