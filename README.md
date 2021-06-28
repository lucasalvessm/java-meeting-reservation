# Room Reservation Backend

Technologies used:

- Spring Boot
- Spring MVC
- Spring Data
- Lombok
- H2
- Swagger

## Setup

### Prerequisites

Java 11 or Docker

### Running with java:

```bash
  ./mvnw clean install
  ./mvnw spring-boot:run
```

### Running with Docker:

```bash
  docker build -t meeting-control .
  docker run meeting-control
```

## Documentation

- Documentation (Swagger): http://localhost:8080/swagger-ui.html
- OpenAPI Spec: http://localhost:8080/v3/api-docs