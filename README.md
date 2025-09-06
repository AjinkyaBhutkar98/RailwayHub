# 🚆 RailConnect (Spring Boot Railway Reservation System)

RailConnect is a **Spring Boot–based monolithic web application** inspired by the IRCTC platform. It provides an end-to-end railway reservation experience, allowing users to **register, authenticate securely, browse train schedules, book tickets, upload profile images, make payments, and cancel reservations** with ease.

The system is designed with modern backend practices, making it both **developer-friendly** and **production-ready**.

---

## ✨ Features

* 🔐 **JWT Authentication & Authorization**

  * Secure login and session management using **Spring Security** and **JWT tokens**.
  * Role-based access control for users and admins.

* 📅 **Train Scheduling & Booking**

  * Search and filter train schedules with **pagination** for performance.
  * Reserve tickets with real-time availability checks.

* 💳 **Payment & Cancellation Module**

  * Mock payment gateway integration for ticket booking.
  * Cancellation workflows with status updates.

* 🖼️ **Image Upload**

  * Upload and manage profile pictures using Spring Boot file-handling.

* 🛠️ **Developer Productivity Enhancements**

  * **Lombok** for reducing boilerplate code.
  * **Builder pattern** for clean object creation.
  * **Bean Validators** for enforcing data integrity at the API level.

* 🗄️ **Database Integration**

  * **MySQL** as the relational database.
  * JPA/Hibernate ORM support with schema validation.

* 📦 **Monolithic Architecture**

  * Built as a single deployable Spring Boot service for simplicity and faster iteration.

---

## 🛠️ Tech Stack

* **Backend Framework:** Spring Boot (with Spring Security, Spring Data JPA)
* **Authentication:** JWT (JSON Web Token)
* **ORM:** Hibernate / JPA
* **Database:** MySQL
* **Utilities:** Lombok, Bean Validation (JSR 380)
* **Build Tool:** Maven / Gradle
* **Architecture:** Monolithic

---

## 🚀 Getting Started

1. **Clone the repository**

   ```bash
   git clone https://github.com/your-username/railconnect.git
   cd railconnect
   ```

2. **Configure the database**

   * Update your `application.properties` with MySQL credentials:

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/railconnect
     spring.datasource.username=root
     spring.datasource.password=yourpassword
     ```

3. **Run the application**

   ```bash
   mvn spring-boot:run
   ```

4. **Access APIs**

   * Swagger / Postman collection included for API testing.
   * Example endpoints:

     * `POST /auth/login` → Authenticate user with JWT
     * `GET /trains?page=0&size=10` → Paginated list of trains
     * `POST /tickets/book` → Book a train ticket

---

## 📌 Future Enhancements

* Role-based admin panel for managing trains and schedules.
* Microservices-based refactoring for scalability.
* Integration with real payment gateways.
* Email/SMS notifications for booking confirmations.

---

## 📖 License

This project is developed for **learning and demonstration purposes**. Feel free to fork, contribute, and enhance.
