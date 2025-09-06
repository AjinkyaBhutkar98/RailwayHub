# 🚆 RailwayHub (Spring Boot Railway Reservation System)

RailwayHub is a **Spring Boot–based monolithic web application** inspired by the IRCTC platform. It enables users to **register, authenticate securely, browse train schedules, book tickets, upload profile images, make payments, and cancel reservations**.

It also includes an **Admin Panel** where administrators can **manage trains, stations, and seat configurations**, making it a complete end-to-end railway reservation system.

---

## ✨ Features

### 👤 User Features

* 🔐 **Secure Authentication**

  * JWT-based login & role-based access control with **Spring Security**.
* 📅 **Train Schedules & Booking**

  * Search and filter trains with **pagination**.
  * Book tickets and view reservation history.
* 💳 **Payments & Cancellations**

  * Mock payment integration for booking tickets.
  * Ticket cancellation workflows with refund status updates.
* 🖼️ **Profile Management**

  * Upload and manage profile images.

### 🛠️ Admin Features

* 🚂 **Train Management** – Add, update, and remove train details.
* 🪑 **Seat Management** – Configure seat layouts and availability.
* 🏙️ **Station Management** – Manage stations and routes.
* 📊 **Dashboard** – Monitor booking activities and system usage.

---

## 🛠️ Tech Stack

* **Framework:** Spring Boot (with Spring Security, Spring Data JPA)
* **Authentication:** JWT (JSON Web Token)
* **Database:** MySQL
* **ORM:** Hibernate / JPA
* **Libraries:** Lombok, Bean Validation (JSR 380)
* **Architecture:** Monolithic
* **Build Tool:** Maven / Gradle

---

## 🚀 Getting Started

1. **Clone the repository**

   ```bash
   git clone https://github.com/your-username/railwayhub.git
   cd railwayhub
   ```

2. **Configure the database**
   Update `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/railwayhub
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```

3. **Run the application**

   ```bash
   mvn spring-boot:run
   ```

4. **Access APIs**

   * Swagger/Postman collection available for testing.
   * Example endpoints:

     * `POST /auth/login` → Authenticate user/admin with JWT
     * `GET /trains?page=0&size=10` → Paginated list of trains
     * `POST /tickets/book` → Book a train ticket
     * `POST /admin/trains` → Add a new train (Admin only)
     * `POST /admin/stations` → Add a new station (Admin only)

---

## 📌 Future Enhancements

* Role-based admin dashboard UI with analytics.
* Real-time train tracking integration.
* Microservices-based refactoring for scalability.
* Email/SMS notifications for bookings & cancellations.

---

## 📖 License

This project is developed for **educational and demonstration purposes**. Contributions are welcome!

---
