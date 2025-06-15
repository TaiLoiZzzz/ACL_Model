
## 1️⃣ Prerequisite

> Cài đặt các công cụ sau trước khi chạy ứng dụng:

- Java 17+
- Maven 3.5+
- IntelliJ IDEA
- Docker Desktop

## 2️⃣ Tech Stack

- Spring Boot 3.3.4
- Spring Data Validation
- Spring Data JPA
- PostgreSQL / MySQL (tuỳ chọn)
- Lombok
- DevTools
- Docker
- Docker Compose

## 3️⃣ Build & Run

### ✅ Chạy bằng Maven Wrapper

🐳 Chạy bằng Docker

./mvnw spring-boot:run
mvn clean install -P dev
docker build -t backend-service:latest .
docker run -it -p 8080:8080 --name backend-service backend-service:latest

4️⃣ Test Health
bash
Copy
Edit
curl http://localhost:8080/actuator/health
📥 Kết quả mong đợi:

json
Copy
Edit
{
  "status": "UP"
}
🌐 Swagger UI
Swagger UI: http://localhost:8080/swagger-ui.html

OpenAPI Docs: http://localhost:8080/v3/api-docs

