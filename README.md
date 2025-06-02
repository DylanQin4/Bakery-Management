# Bakery-Management - Spring MVC with Tailwind CSS, Flowbite, and Thymeleaf

## Description
This project is an application for managing a bakery, built using **Spring MVC**, **Thymeleaf**, **Tailwind CSS**, and **Flowbite** for a modern and efficient web interface.

## Prerequisites
Before starting, make sure you have installed the following tools:

- **PostgreSQL 14** or later
- **Java 17** or later
- **Maven** for dependency management
- **Node.js** and **npm** for front-end dependency management

## Installation

### 1. Clone the repository
```bash
git clone https://github.com/DylanQin4/Bakery-Management.git
cd Bakery-Management
```

### 2. Configure back-end dependencies
Ensure Maven is configured and install Java dependencies by running:
```bash
mvn clean install
```

### 3. Configure front-end dependencies
Navigate to the front-end directory and install npm dependencies:

```bash
cd src/main/resources/static
npm install
```

### 4. Compile Tailwind CSS
To compile the CSS with **Tailwind CSS** and **Flowbite**, run:

```bash
npm run build
```

This will generate a `tailwind-output.css` file in the `static/css` directory.

## Usage

### Launch the application
Run the Spring Boot application with the following command:
```bash
mvn spring-boot:run
```

Access the application in your browser at:
```
http://localhost:8080
```
