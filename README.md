# Gym Membership Management System

## Backend REST API

The Gym Membership Management System is a Spring Boot backend application designed to manage the daily operations of a small or medium-sized gym.

The system provides REST API endpoints for:

* Members
* Trainers
* Managers
* Administrators
* Membership plans
* Appointments
* Attendance
* User login and account management

The frontend application communicates with this backend by sending HTTP requests and receiving JSON responses.

---

## Table of Contents

1. Project Overview
2. Technologies
3. Project Structure
4. User Roles
5. System Features
6. Requirements
7. Database Setup
8. Environment Variables
9. Running the Application
10. Frontend Connection
11. API Testing
12. API Endpoints
13. JSON Request Examples
14. HTTP Status Codes
15. Validation
16. Error Responses
17. Entity Relationships
18. Security Notes
19. Current Limitations
20. Recommended Future Improvements
21. Frontend Handoff Checklist

---

# 1. Project Overview

This repository contains the backend for the Gym Membership Management System.

The backend is responsible for:

* Storing gym data in MySQL
* Creating and managing user accounts
* Creating and managing membership plans
* Scheduling trainer appointments
* Recording member attendance
* Validating incoming data
* Preventing duplicate records
* Returning structured JSON responses
* Providing REST endpoints for the frontend team

The frontend team does not need to use Eclipse. The frontend can be created with React, Angular, Vue, or another user-interface framework as long as it sends HTTP requests to this backend.

---

# 2. Technologies

The backend uses:

* Java 21
* Spring Boot 4.1.0
* Spring Web
* Spring Data JPA
* Hibernate
* Jakarta Validation
* MySQL
* Gradle
* BCrypt password hashing
* Eclipse IDE with Spring Tools
* RESTful API architecture

---

# 3. Project Structure

```text
src
└── main
    ├── java
    │   └── com.gym
    │       ├── config
    │       │   ├── CorsConfig.java
    │       │   └── PasswordConfig.java
    │       │
    │       ├── controller
    │       │   ├── AdministratorController.java
    │       │   ├── AppointmentController.java
    │       │   ├── AttendanceController.java
    │       │   ├── ManagerController.java
    │       │   ├── MemberController.java
    │       │   ├── MembershipController.java
    │       │   ├── TrainerController.java
    │       │   └── UserController.java
    │       │
    │       ├── dto
    │       │   ├── AppointmentRequest.java
    │       │   ├── AppointmentStatusRequest.java
    │       │   ├── AttendanceRequest.java
    │       │   ├── ChangeEmailRequest.java
    │       │   ├── ChangePasswordRequest.java
    │       │   ├── CheckOutRequest.java
    │       │   ├── LoginRequest.java
    │       │   ├── LoginResponse.java
    │       │   └── UpdateProfileRequest.java
    │       │
    │       ├── exception
    │       │   ├── ApiError.java
    │       │   ├── DuplicateResourceException.java
    │       │   ├── GlobalExceptionHandler.java
    │       │   └── ResourceNotFoundException.java
    │       │
    │       ├── model
    │       │   ├── Administrator.java
    │       │   ├── Appointment.java
    │       │   ├── AppointmentStatus.java
    │       │   ├── Attendance.java
    │       │   ├── Manager.java
    │       │   ├── Member.java
    │       │   ├── Membership.java
    │       │   ├── Trainer.java
    │       │   └── User.java
    │       │
    │       ├── repository
    │       ├── service
    │       └── GymManagementSystemApplication.java
    │
    └── resources
        └── application.properties
```

---

# 4. User Roles

The system supports four user roles.

## Member

Members can:

* Create an account
* Log in
* View their account information
* View membership information
* Book trainer appointments
* View appointments
* View attendance history
* Update profile information
* Change email
* Change password

## Trainer

Trainers can:

* Log in
* View trainer information
* View assigned appointments
* View scheduled sessions
* Manage trainer profile information
* Change email
* Change password

## Administrator

Administrators can:

* Manage members
* Manage trainers
* Manage membership plans
* Manage appointments
* Manage attendance records
* Manage user records
* Update administrator profile information

## Manager

Managers can:

* Manage gym operations
* Manage staff information
* View gym information
* Review attendance and appointment data
* Update manager profile information

The current backend identifies the role of a user during login. Full endpoint-level authorization has not yet been implemented.

---

# 5. System Features

The backend currently includes:

* CRUD operations for members
* CRUD operations for trainers
* CRUD operations for managers
* CRUD operations for administrators
* CRUD operations for memberships
* CRUD operations for appointments
* CRUD operations for attendance
* Login validation
* BCrypt password hashing
* User profile updates
* Password changes
* Email changes
* Appointment filtering by member
* Appointment filtering by trainer
* Appointment filtering by date
* Appointment status updates
* Attendance history by member
* Separate attendance checkout operation
* Duplicate-email validation
* Duplicate-membership validation
* Trainer scheduling conflict validation
* Centralized exception handling
* Cross-origin resource sharing configuration

---

# 6. Requirements

Install the following software before running the project:

* Java Development Kit 21
* MySQL Server
* MySQL Workbench, optional
* Eclipse IDE with Spring Tools, optional
* Gradle support
* Postman, Insomnia, or another API-testing application

Verify Java installation:

```bash
java -version
```

Verify that the project can access Gradle:

### Windows

```bash
gradlew.bat --version
```

### macOS or Linux

```bash
./gradlew --version
```

---

# 7. Database Setup

Start MySQL and create the database:

```sql
CREATE DATABASE gymdb;
```

To verify that it exists:

```sql
SHOW DATABASES;
```

Select the database:

```sql
USE gymdb;
```

The application uses Hibernate with:

```properties
spring.jpa.hibernate.ddl-auto=update
```

Hibernate will create or update the required tables when the backend starts.

The database tables should not normally need to be created manually.

---

# 8. Environment Variables

The application reads its database connection values from environment variables.

The current configuration uses:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/gymdb}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}
```

The database password should not be typed directly into `application.properties` or committed to GitHub.

## Windows Eclipse configuration

1. Open Eclipse.
2. Select **Run**.
3. Select **Run Configurations**.
4. Select the Spring Boot application.
5. Open the **Environment** tab.
6. Add the following variables:

```text
DB_URL=jdbc:mysql://localhost:3306/gymdb
DB_USERNAME=root
DB_PASSWORD=your_mysql_password
```

Replace `your_mysql_password` with the actual MySQL password.

## Windows PowerShell

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/gymdb"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your_mysql_password"
.\gradlew.bat bootRun
```

## Windows Command Prompt

```cmd
set DB_URL=jdbc:mysql://localhost:3306/gymdb
set DB_USERNAME=root
set DB_PASSWORD=your_mysql_password
gradlew.bat bootRun
```

## macOS or Linux

```bash
export DB_URL="jdbc:mysql://localhost:3306/gymdb"
export DB_USERNAME="root"
export DB_PASSWORD="your_mysql_password"
./gradlew bootRun
```

---

# 9. Required Gradle Dependencies

The `dependencies` section of `build.gradle` should include:

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'

    implementation 'org.springframework.security:spring-security-crypto'

    runtimeOnly 'com.mysql:mysql-connector-j'

    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```

The Spring Security Crypto dependency provides the BCrypt password encoder. It does not automatically add login-page security or block the API endpoints.

After editing `build.gradle`, refresh the project:

1. Right-click the project in Eclipse.
2. Select **Gradle**.
3. Select **Refresh Gradle Project**.

---

# 10. Running the Application

## Clone the repository

```bash
git clone <repository-url>
```

Alternatively, download and extract the project folder.

## Open in Eclipse

1. Select **File**.
2. Select **Import**.
3. Select **Gradle**.
4. Select **Existing Gradle Project**.
5. Select the project folder.
6. Finish the import.
7. Allow Gradle to download the dependencies.

## Run from Eclipse

Run:

```text
GymManagementSystemApplication.java
```

Select:

```text
Run As → Spring Boot App
```

## Run from the terminal

### Windows

```bash
gradlew.bat bootRun
```

### macOS or Linux

```bash
./gradlew bootRun
```

The backend should start at:

```text
http://localhost:8080
```

---

# 11. Frontend Connection

The frontend should use this local base URL:

```text
http://localhost:8080
```

Example request:

```text
GET http://localhost:8080/members
```

JSON requests must include:

```http
Content-Type: application/json
```

The current CORS configuration allows frontend applications running at:

```text
http://localhost:3000
http://localhost:4200
http://localhost:5173
```

These commonly correspond to:

* React on port 3000
* Angular on port 4200
* Vite on port 5173

If the frontend uses another port or production domain, add that origin to `CorsConfig.java`.

Example JavaScript request:

```javascript
fetch("http://localhost:8080/members")
    .then(response => {
        if (!response.ok) {
            throw new Error("Request failed");
        }

        return response.json();
    })
    .then(data => console.log(data))
    .catch(error => console.error(error));
```

Example POST request:

```javascript
fetch("http://localhost:8080/memberships", {
    method: "POST",
    headers: {
        "Content-Type": "application/json"
    },
    body: JSON.stringify({
        membershipType: "Premium",
        price: 59.99,
        durationMonths: 12
    })
})
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
```

---

# 12. API Testing

The API can be tested with:

* Postman
* Insomnia
* curl
* A frontend application

For POST, PUT, and PATCH requests in Postman:

1. Select the HTTP method.
2. Enter the endpoint URL.
3. Select **Body**.
4. Select **raw**.
5. Select **JSON**.
6. Paste the required JSON.
7. Select **Send**.

Use this header:

```http
Content-Type: application/json
```

GET and DELETE requests normally do not have a request body.

---

# 13. Recommended Record-Creation Order

Create records in this order:

1. Membership
2. Member
3. Trainer
4. Manager
5. Administrator
6. Appointment
7. Attendance

Appointments require an existing member and trainer.

Attendance records require an existing member.

Use the IDs returned by the API. Do not assume that the first trainer has ID `1`, because all user types inherit their IDs from the shared user table.

---

# 14. API Endpoints

## Base URL

```text
http://localhost:8080
```

---

## User Account Endpoints

| Method | Endpoint               | Purpose                      |
| ------ | ---------------------- | ---------------------------- |
| GET    | `/users/{id}`          | Get a user by ID             |
| POST   | `/users/login`         | Validate login credentials   |
| PUT    | `/users/{id}/profile`  | Update name and phone number |
| PUT    | `/users/{id}/password` | Change a user's password     |
| PUT    | `/users/{id}/email`    | Change a user's email        |

---

## Member Endpoints

| Method | Endpoint        | Purpose         |
| ------ | --------------- | --------------- |
| GET    | `/members`      | Get all members |
| GET    | `/members/{id}` | Get one member  |
| POST   | `/members`      | Create a member |
| PUT    | `/members/{id}` | Update a member |
| DELETE | `/members/{id}` | Delete a member |

---

## Trainer Endpoints

| Method | Endpoint         | Purpose          |
| ------ | ---------------- | ---------------- |
| GET    | `/trainers`      | Get all trainers |
| GET    | `/trainers/{id}` | Get one trainer  |
| POST   | `/trainers`      | Create a trainer |
| PUT    | `/trainers/{id}` | Update a trainer |
| DELETE | `/trainers/{id}` | Delete a trainer |

---

## Manager Endpoints

| Method | Endpoint         | Purpose          |
| ------ | ---------------- | ---------------- |
| GET    | `/managers`      | Get all managers |
| GET    | `/managers/{id}` | Get one manager  |
| POST   | `/managers`      | Create a manager |
| PUT    | `/managers/{id}` | Update a manager |
| DELETE | `/managers/{id}` | Delete a manager |

---

## Administrator Endpoints

| Method | Endpoint               | Purpose                 |
| ------ | ---------------------- | ----------------------- |
| GET    | `/administrators`      | Get all administrators  |
| GET    | `/administrators/{id}` | Get one administrator   |
| POST   | `/administrators`      | Create an administrator |
| PUT    | `/administrators/{id}` | Update an administrator |
| DELETE | `/administrators/{id}` | Delete an administrator |

---

## Membership Endpoints

| Method | Endpoint            | Purpose                  |
| ------ | ------------------- | ------------------------ |
| GET    | `/memberships`      | Get all membership plans |
| GET    | `/memberships/{id}` | Get one membership plan  |
| POST   | `/memberships`      | Create a membership plan |
| PUT    | `/memberships/{id}` | Update a membership plan |
| DELETE | `/memberships/{id}` | Delete a membership plan |

---

## Appointment Endpoints

| Method | Endpoint                            | Purpose                        |
| ------ | ----------------------------------- | ------------------------------ |
| GET    | `/appointments`                     | Get all appointments           |
| GET    | `/appointments?date=YYYY-MM-DD`     | Get appointments on a date     |
| GET    | `/appointments/{id}`                | Get one appointment            |
| GET    | `/appointments/member/{memberId}`   | Get appointments for a member  |
| GET    | `/appointments/trainer/{trainerId}` | Get appointments for a trainer |
| POST   | `/appointments`                     | Create an appointment          |
| PUT    | `/appointments/{id}`                | Update an appointment          |
| PATCH  | `/appointments/{id}/status`         | Change appointment status      |
| DELETE | `/appointments/{id}`                | Delete an appointment          |

---

## Attendance Endpoints

| Method | Endpoint                        | Purpose                             |
| ------ | ------------------------------- | ----------------------------------- |
| GET    | `/attendance`                   | Get all attendance records          |
| GET    | `/attendance/{id}`              | Get one attendance record           |
| GET    | `/attendance/member/{memberId}` | Get attendance history for a member |
| POST   | `/attendance`                   | Create a check-in record            |
| PUT    | `/attendance/{id}`              | Update an attendance record         |
| PATCH  | `/attendance/{id}/check-out`    | Record a checkout time              |
| DELETE | `/attendance/{id}`              | Delete an attendance record         |

---

# 15. JSON Request Examples

## Create a Membership

### Request

```http
POST /memberships
```

### JSON

```json
{
  "membershipType": "Premium",
  "price": 59.99,
  "durationMonths": 12
}
```

---

## Create a Member

Create the membership first and replace `1` with the actual membership ID.

### Request

```http
POST /members
```

### JSON

```json
{
  "firstName": "John",
  "lastName": "Smith",
  "email": "john.smith@example.com",
  "password": "Password123",
  "phoneNumber": "555-123-4567",
  "membership": {
    "membershipId": 1
  }
}
```

---

## Create a Trainer

### Request

```http
POST /trainers
```

### JSON

```json
{
  "firstName": "Sarah",
  "lastName": "Williams",
  "email": "sarah.williams@example.com",
  "password": "Password123",
  "phoneNumber": "555-234-5678",
  "specialization": "Strength Training"
}
```

---

## Create a Manager

### Request

```http
POST /managers
```

### JSON

```json
{
  "firstName": "Robert",
  "lastName": "Davis",
  "email": "robert.davis@example.com",
  "password": "Password123",
  "phoneNumber": "555-456-7890",
  "department": "Gym Operations"
}
```

---

## Create an Administrator

### Request

```http
POST /administrators
```

### JSON

```json
{
  "firstName": "Linda",
  "lastName": "Taylor",
  "email": "linda.taylor@example.com",
  "password": "Password123",
  "phoneNumber": "555-567-8901",
  "role": "ADMINISTRATOR"
}
```

---

## Create an Appointment

Replace `memberId` and `trainerId` with existing IDs returned by the API.

### Request

```http
POST /appointments
```

### JSON

```json
{
  "appointmentDate": "2026-07-25",
  "appointmentTime": "14:30:00",
  "memberId": 1,
  "trainerId": 2,
  "status": "SCHEDULED"
}
```

The accepted appointment statuses are:

```text
SCHEDULED
COMPLETED
CANCELLED
```

The status can be omitted when creating an appointment. The backend will use `SCHEDULED` by default.

```json
{
  "appointmentDate": "2026-07-25",
  "appointmentTime": "14:30:00",
  "memberId": 1,
  "trainerId": 2
}
```

Date format:

```text
YYYY-MM-DD
```

Time format:

```text
HH:mm:ss
```

---

## Create an Attendance Record

### Request

```http
POST /attendance
```

### JSON

```json
{
  "attendanceDate": "2026-07-19",
  "checkInTime": "09:00:00",
  "memberId": 1
}
```

A completed attendance record can include checkout time:

```json
{
  "attendanceDate": "2026-07-19",
  "checkInTime": "09:00:00",
  "checkOutTime": "10:30:00",
  "memberId": 1
}
```

---

## User Login

### Request

```http
POST /users/login
```

### JSON

```json
{
  "email": "john.smith@example.com",
  "password": "Password123"
}
```

### Example successful response

```json
{
  "userId": 1,
  "firstName": "John",
  "lastName": "Smith",
  "email": "john.smith@example.com",
  "phoneNumber": "555-123-4567",
  "role": "MEMBER"
}
```

The current login endpoint does not return a JWT or session token.

---

## Update a Membership

### Request

```http
PUT /memberships/1
```

### JSON

```json
{
  "membershipType": "Premium Plus",
  "price": 69.99,
  "durationMonths": 12
}
```

---

## Update a Member

### Request

```http
PUT /members/1
```

### JSON

```json
{
  "firstName": "John",
  "lastName": "Smith",
  "email": "john.smith@example.com",
  "password": "Password123",
  "phoneNumber": "555-999-8888",
  "membership": {
    "membershipId": 1
  }
}
```

The current member update endpoint validates a complete member object. A password may therefore be required in the request even though profile updates should not change the password.

Use `/users/{id}/password` to change a password.

---

## Update a Trainer

### Request

```http
PUT /trainers/2
```

### JSON

```json
{
  "firstName": "Sarah",
  "lastName": "Williams",
  "email": "sarah.williams@example.com",
  "password": "Password123",
  "phoneNumber": "555-111-9898",
  "specialization": "Strength and Conditioning"
}
```

---

## Update a Manager

### Request

```http
PUT /managers/3
```

### JSON

```json
{
  "firstName": "Robert",
  "lastName": "Davis",
  "email": "robert.davis@example.com",
  "password": "Password123",
  "phoneNumber": "555-000-1111",
  "department": "General Operations"
}
```

---

## Update an Administrator

### Request

```http
PUT /administrators/4
```

### JSON

```json
{
  "firstName": "Linda",
  "lastName": "Taylor",
  "email": "linda.taylor@example.com",
  "password": "Password123",
  "phoneNumber": "555-222-4444",
  "role": "ADMINISTRATOR"
}
```

---

## Update an Appointment

### Request

```http
PUT /appointments/1
```

### JSON

```json
{
  "appointmentDate": "2026-07-28",
  "appointmentTime": "16:00:00",
  "memberId": 1,
  "trainerId": 2,
  "status": "SCHEDULED"
}
```

---

## Change Appointment Status

### Request

```http
PATCH /appointments/1/status
```

### JSON

```json
{
  "status": "COMPLETED"
}
```

Other accepted values:

```json
{
  "status": "CANCELLED"
}
```

```json
{
  "status": "SCHEDULED"
}
```

---

## Update Attendance

### Request

```http
PUT /attendance/1
```

### JSON

```json
{
  "attendanceDate": "2026-07-19",
  "checkInTime": "08:45:00",
  "checkOutTime": "10:15:00",
  "memberId": 1
}
```

---

## Check a Member Out

### Request

```http
PATCH /attendance/1/check-out
```

### JSON

```json
{
  "checkOutTime": "10:30:00"
}
```

---

## Update User Profile

### Request

```http
PUT /users/1/profile
```

### JSON

```json
{
  "firstName": "Jonathan",
  "lastName": "Smith",
  "phoneNumber": "555-777-8888"
}
```

This endpoint updates only the first name, last name, and phone number.

---

## Change Password

### Request

```http
PUT /users/1/password
```

### JSON

```json
{
  "currentPassword": "Password123",
  "newPassword": "NewPassword456"
}
```

---

## Change Email

### Request

```http
PUT /users/1/email
```

### JSON

```json
{
  "password": "NewPassword456",
  "newEmail": "jonathan.smith@example.com"
}
```

---

# 16. GET Request Examples

Get all members:

```http
GET http://localhost:8080/members
```

Get one member:

```http
GET http://localhost:8080/members/1
```

Get all trainers:

```http
GET http://localhost:8080/trainers
```

Get all appointments:

```http
GET http://localhost:8080/appointments
```

Get appointments on a specific date:

```http
GET http://localhost:8080/appointments?date=2026-07-25
```

Get a member's appointments:

```http
GET http://localhost:8080/appointments/member/1
```

Get a trainer's appointments:

```http
GET http://localhost:8080/appointments/trainer/2
```

Get a member's attendance history:

```http
GET http://localhost:8080/attendance/member/1
```

GET requests should not include a JSON request body.

---

# 17. DELETE Request Examples

Delete a member:

```http
DELETE http://localhost:8080/members/1
```

Delete a trainer:

```http
DELETE http://localhost:8080/trainers/2
```

Delete an appointment:

```http
DELETE http://localhost:8080/appointments/1
```

Delete an attendance record:

```http
DELETE http://localhost:8080/attendance/1
```

DELETE requests should not include a JSON request body.

---

# 18. HTTP Status Codes

The frontend should expect the following status codes.

| Status                      | Meaning                                                 |
| --------------------------- | ------------------------------------------------------- |
| `200 OK`                    | Request completed successfully                          |
| `201 Created`               | A new record was created                                |
| `204 No Content`            | A record was deleted successfully                       |
| `400 Bad Request`           | Invalid JSON, failed validation, or invalid credentials |
| `404 Not Found`             | The requested record does not exist                     |
| `409 Conflict`              | A duplicate record or scheduling conflict exists        |
| `500 Internal Server Error` | An unexpected backend error occurred                    |

The frontend should check `response.ok` or the HTTP status before assuming a request succeeded.

---

# 19. Validation Rules

The backend validates incoming information.

## User validation

* First name is required.
* Last name is required.
* Email is required.
* Email must use a valid format.
* Email must be unique.
* Password is required when creating an account.
* Password must meet the configured length requirements.
* Phone number must meet the configured format requirements.

## Membership validation

* Membership type is required.
* Price must be greater than zero.
* Duration must be at least one month.
* Duplicate membership types are not allowed.

## Trainer validation

* Specialization is required.
* Specialization cannot exceed the configured maximum length.

## Manager validation

* Department is required.
* Department cannot exceed the configured maximum length.

## Administrator validation

* Role is required.
* The expected administrator role is:

```text
ADMINISTRATOR
```

## Appointment validation

* Appointment date is required.
* Appointment time is required.
* Member ID is required.
* Trainer ID is required.
* Appointment date cannot be in the past.
* A trainer cannot be booked twice at the same date and time.
* Status must be one of:

```text
SCHEDULED
COMPLETED
CANCELLED
```

## Attendance validation

* Attendance date is required.
* Check-in time is required.
* Member ID is required.
* Attendance date cannot be in the future.
* Checkout time can be added later.

---

# 20. Error Responses

The backend uses centralized exception handling.

A validation error may resemble:

```json
{
  "timestamp": "2026-07-19T21:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Email must be valid",
  "path": "/members"
}
```

A missing resource may resemble:

```json
{
  "timestamp": "2026-07-19T21:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Member not found with ID: 99",
  "path": "/members/99"
}
```

A duplicate or scheduling conflict may resemble:

```json
{
  "timestamp": "2026-07-19T21:00:00",
  "status": 409,
  "error": "Conflict",
  "message": "Trainer already has an appointment at that date and time",
  "path": "/appointments"
}
```

The exact field arrangement depends on the current `ApiError` and `GlobalExceptionHandler` implementation.

---

# 21. Entity Relationships

The user types use inheritance:

```text
User
├── Member
├── Trainer
├── Administrator
└── Manager
```

The main relationships are:

```text
Membership
    └── Members

Member
    ├── Membership
    ├── Appointments
    └── Attendance Records

Trainer
    └── Appointments

Appointment
    ├── Member
    └── Trainer

Attendance
    └── Member
```

Conceptual relationship summary:

```text
Membership 1 ──────── * Member

Member     1 ──────── * Appointment
Trainer    1 ──────── * Appointment

Member     1 ──────── * Attendance
```

---

# 22. Password Security

Passwords are hashed using BCrypt before they are stored in MySQL.

Plain-text passwords should never be:

* Returned in API responses
* Printed in application logs
* Committed to GitHub
* Stored in README files
* Stored directly in the database
* Shared with the frontend after account creation

During login, the frontend sends the entered password over the API. The backend compares it with the BCrypt hash in the database.

This project should use HTTPS when deployed outside a local development environment.

---

# 23. Current Authentication Limitation

The current login endpoint verifies the email and password and returns user information with a role.

It does not currently create:

* A JWT
* A server session
* An access token
* A refresh token

This means the frontend can identify the logged-in user, but the backend does not yet enforce protected endpoints based on authentication.

For a class demonstration, this may be acceptable when clearly documented.

For a production system, JWT or session-based authentication and role-based authorization should be added.

---

# 24. API Response Considerations

The current backend may return JPA entity objects directly.

The frontend should not depend on:

* Internal Hibernate properties
* Database table names
* Password fields
* Circular relationship structures

For long-term development, dedicated response DTOs are recommended for:

* Member responses
* Trainer responses
* Manager responses
* Administrator responses
* Appointment responses
* Attendance responses
* Membership responses

Response DTOs provide a stable contract between the frontend and backend.

---

# 25. Testing Checklist

Before handing the project to the frontend team, test every item below.

## Application startup

* [ ] MySQL is running.
* [ ] The `gymdb` database exists.
* [ ] `DB_PASSWORD` is configured.
* [ ] Gradle dependencies load successfully.
* [ ] The application starts without errors.
* [ ] The server starts on port 8080.

## Membership testing

* [ ] Create a membership.
* [ ] Get all memberships.
* [ ] Get one membership.
* [ ] Update a membership.
* [ ] Delete a test membership.
* [ ] Test duplicate membership validation.

## Member testing

* [ ] Create a member.
* [ ] Confirm the password is hashed in MySQL.
* [ ] Get all members.
* [ ] Get one member.
* [ ] Update a member.
* [ ] Delete a test member.
* [ ] Test duplicate email validation.

## Trainer testing

* [ ] Create a trainer.
* [ ] Get all trainers.
* [ ] Get one trainer.
* [ ] Update a trainer.
* [ ] Delete a test trainer.

## Manager testing

* [ ] Create a manager.
* [ ] Get all managers.
* [ ] Get one manager.
* [ ] Update a manager.
* [ ] Delete a test manager.

## Administrator testing

* [ ] Create an administrator.
* [ ] Get all administrators.
* [ ] Get one administrator.
* [ ] Update an administrator.
* [ ] Delete a test administrator.

## Appointment testing

* [ ] Create an appointment.
* [ ] Get all appointments.
* [ ] Get an appointment by ID.
* [ ] Filter appointments by date.
* [ ] Get appointments by member.
* [ ] Get appointments by trainer.
* [ ] Update an appointment.
* [ ] Change appointment status.
* [ ] Test duplicate trainer scheduling.
* [ ] Delete a test appointment.

## Attendance testing

* [ ] Create a check-in record.
* [ ] Get all attendance records.
* [ ] Get attendance by ID.
* [ ] Get attendance history by member.
* [ ] Add checkout time.
* [ ] Update an attendance record.
* [ ] Delete a test attendance record.

## User account testing

* [ ] Log in with a member account.
* [ ] Log in with a trainer account.
* [ ] Log in with a manager account.
* [ ] Log in with an administrator account.
* [ ] Test an incorrect password.
* [ ] Test an unknown email.
* [ ] Update profile information.
* [ ] Change password.
* [ ] Log in with the new password.
* [ ] Change email.
* [ ] Log in with the new email.

## Frontend connection testing

* [ ] Frontend can reach `http://localhost:8080`.
* [ ] CORS allows the frontend origin.
* [ ] JSON request headers are set correctly.
* [ ] The frontend handles 400 errors.
* [ ] The frontend handles 404 errors.
* [ ] The frontend handles 409 errors.
* [ ] The frontend does not display password data.
* [ ] The frontend uses IDs returned by the backend.

---

# 26. Information to Give the Frontend Team

Provide the frontend team with:

1. This README
2. The complete backend project folder
3. The API base URL
4. The API endpoint list
5. The required JSON bodies
6. The accepted appointment status values
7. The date and time formats
8. The expected HTTP status codes
9. The expected error response format
10. Test account credentials
11. Sample database records
12. A Postman collection
13. Instructions for running the backend
14. Instructions for setting database environment variables
15. A note explaining that login currently returns no token

The local API base URL is:

```text
http://localhost:8080
```

The frontend should not receive the MySQL database password.

The frontend communicates only with the REST API and does not connect directly to MySQL.

---

# 27. Information That Must Not Be Shared or Committed

Do not commit or send the following through a public repository:

* MySQL passwords
* Production database URLs
* Real user passwords
* Secret keys
* Future JWT signing keys
* Private API keys
* Personal member data

Use environment variables for secrets.

Recommended `.gitignore` entries include:

```gitignore
.gradle/
build/
bin/
.settings/
.project
.classpath

.env
application-local.properties

*.log
```

Do not ignore the main `application.properties` file when it contains only environment-variable placeholders.

---

# 28. Recommended Future Improvements

Recommended improvements include:

* JWT authentication
* Role-based endpoint authorization
* Refresh tokens
* Logout token invalidation
* Email verification
* Password-recovery workflow
* Dedicated request DTOs for all update operations
* Dedicated response DTOs
* Pagination
* Sorting
* Search endpoints
* Dashboard summary endpoints
* Revenue reports
* Attendance reports
* Membership reports
* Trainer-availability endpoints
* Appointment duration
* Appointment notes
* Automated unit tests
* Integration tests
* OpenAPI and Swagger documentation
* Docker configuration
* Production database migrations with Flyway or Liquibase
* Deployment configuration
* HTTPS
* Audit logging

---

# 29. Suggested Swagger Dependency

Swagger is not required for the frontend to begin development, but it can provide interactive API documentation.

Before adding Swagger, verify that the selected Springdoc version supports the version of Spring Boot used by this project.

After Swagger is configured, the documentation is commonly available at a route similar to:

```text
http://localhost:8080/swagger-ui/index.html
```

The exact route and dependency version depend on the selected Springdoc release.

---

# 30. Building the Project

Run the automated tests:

### Windows

```bash
gradlew.bat test
```

### macOS or Linux

```bash
./gradlew test
```

Create the application JAR:

### Windows

```bash
gradlew.bat clean build
```

### macOS or Linux

```bash
./gradlew clean build
```

The generated JAR should appear in:

```text
build/libs
```

Run the JAR:

```bash
java -jar build/libs/<generated-file-name>.jar
```

The database environment variables must still be available when the JAR is started.

---

# 31. Project Team

**Project:** Gym Membership Management System

**Project type:** Software Engineering course project

**Architecture:** Spring Boot REST API with MySQL persistence

The backend is intended to be integrated with a separately developed frontend application.

---

# Individual Contribution: Backend Development
---

## My Role

I contributed to the backend development of the Gym
Membership Management System as part of a team-based
Software Engineering course project.

My work focused on developing and integrating backend
functionality using Java, Spring Boot, and MySQL.
---

## Backend Responsibilities

My backend work included:

- Working with REST API controllers for gym operations.
- Developing and integrating service-layer functionality.
- Working with JPA entities and repository interfaces.
- Configuring MySQL database connectivity.
- Supporting membership and user management operations.
- Working with appointment and attendance functionality.
- Troubleshooting database relationships and backend errors.
- Testing API operations using Postman.
- Preparing backend documentation for integration.

---

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA / Hibernate
- MySQL
- Gradle
- Eclipse IDE
- Postman

---

## Team Collaboration

This application was developed as a team project.
The backend was designed to communicate with a
separately developed frontend through REST APIs.

This repository showcases my backend development
work and the backend components of the team project.
---

# 32. License and Academic Use

This project was created for academic and educational purposes.

Before using the project outside the course, review:

* Third-party dependency licenses
* Privacy requirements
* Password-security requirements
* Data-retention requirements
* Applicable laws regarding personal information
