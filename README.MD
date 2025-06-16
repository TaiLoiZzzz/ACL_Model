# 🚀 Backend Service - Spring Boot Starter Guide

## 1️⃣ Prerequisite

Trước khi chạy project, hãy đảm bảo:

* ✅ Đã cài đặt **JDK 17+**
* ✅ Đã cài **Maven 3.5+**
* ✅ Sử dụng **IntelliJ IDEA** hoặc IDE bất kỳ
* ✅ Đã cài đặt **Docker** và **Docker Compose**

---

## 2️⃣ Tech Stack

| Công nghệ         | Mô tả                               |
| ----------------- | ----------------------------------- |
| Java              | ✅ Phiên bản 17 trở lên              |
| Spring Boot       | ✅ Phiên bản `3.3.4`                 |
| Maven             | ✅ Build tool chính (`mvnw`)         |
| Spring Data JPA   | Kết nối CSDL                        |
| Spring Validation | Kiểm tra dữ liệu đầu vào            |
| MySQL/PostgreSQL  | Tuùy chọn CSDL                      |
| Lombok            | Tự động hoá getter/setter/toString… |
| DevTools          | Reload nhanh khi code thay đổi      |
| Docker            | Đóng gói ứng dụng                   |
| Docker Compose    | Chạy nhiều service cùng lúc         |

---

## 3️⃣ Build & Run Application

### ▶️ Chạy bằng Maven (local)

```bash
# Trong thư mục backend-service
./mvnw spring-boot:run
```

### 🟣 Chạy bằng Docker

```bash
# Build ứng dụng trước
mvn clean install -P dev

# Build Docker image
docker build -t backend-service:latest .

# Run container
docker run -it -p 8080:8080 --name backend-service backend-service:latest
```

> 📌 Lưu ý: Nếu dùng Docker Compose, port có thể thay đổi (8081, 8083…)

---

## 4️⃣ Kiểm tra hệ thống hoạt động

Dùng `curl` hoặc trình duyệt để test `/actuator/health`:

```bash
curl --location 'http://localhost:8080/actuator/health'
```

### ✅ Response mong đợi:

```json
{
  "status": "UP"
}
```

---

## ✨ Ghi chú thêm

* Swagger UI: `http://localhost:8080/swagger-ui.html`
* Tài liệu API: `http://localhost:8080/v3/api-docs`
* Profile mặc định: `dev`, có thể cấu hình trong `application-dev.yml`

---
