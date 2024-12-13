# Library Microservice

**Library Microservice** is a distributed application built using **Spring Boot**, **Spring Cloud**, and **Eureka** for service discovery. It allows you to manage users, books, and borrowings in a library system with API Gateway routing and inter-service communication.

![Description of Image](https://github.com/kaanacikgoz/Library-Microservice/blob/main/Library-Microservice.png?raw=true)

## Features

- **CRUD Operations** for managing users, books and borrowings
- **Microservice architecture** with Spring Boot and Spring Cloud
- **Service discovery** using Eureka
- **Feign Client** for inter-service communication
- **API Gateway** to route requests to microservices
- **JPA/Hibernate** for database interaction
- **MongoDB** as the database for User Service
- **PostgreSQL** as the database for Book Service
- **MySQL** as the database for Borrowing Service

## Project Structure

- **Book-service**: Handles book-related operations.
- **User-service**: Manages user-related operations.
- **Borrowing-service**: Handles borrowing and returning of books.
- **Api-gateway**: Routes API requests to the correct services.
- **Eureka-server**: Registers and discovers services.

## Prerequisites

Before running the project, make sure you have the following tools installed:

- **Java 17+**
- **Maven**
- **Docker** (optional, for running databases locally)
- **MongoDB** (for User Service)
- **PostgreSQL** (for Book Service)
- **MySQL** for Borrowing Service)

If using Docker, you can skip the local installation of databases. Docker Compose is available to set up all services automatically.

## Setup and Installation

Follow these steps to get the project up and running locally.

### 1. Clone the repository:

```bash
git clone https://github.com/kaanacikgoz/Library-Microservice.git
cd Library-Microservice
```
### 2. Run Docker-compose:
```bash
docker-compose up 
```