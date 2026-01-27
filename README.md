# Sports Club API

Aplicación Java headless para la gestión de un club deportivo con pipeline CI/CD completo.

## Descripción

API REST desarrollada con Spring Boot que permite gestionar atletas, entrenadores y actividades de un club deportivo. El proyecto incluye integración continua automatizada mediante GitHub Actions, contenedorización con Docker y una suite completa de tests unitarios.

## Características principales

- API REST con endpoints CRUD para Athletes, Coaches y Activities
- Persistencia de datos con PostgreSQL
- Validación de datos con Bean Validation
- Tests unitarios automatizados con JUnit 5
- Pipeline CI/CD con GitHub Actions
- Contenedorización con Docker y Docker Compose
- Análisis de calidad de código con Checkstyle

## Arquitectura

### Entidades

- **Athlete**: Representa a los atletas del club
  - Atributos: name, surname, email, birthDate
  - Relación Many-to-One con Coach

- **Coach**: Representa a los entrenadores
  - Atributos: name, surname, email, birthDate, certification
  - Relación One-to-Many con Athletes

- **Activity**: Representa actividades del club
  - Atributos: name, type, dateTime, durationMinutes, location, description
  - Tipos: TRAINING, COMPETITION

### Estructura del proyecto

src/
├── main/
│ ├── java/eu/cifpfbmoll/sportsclub/
│ │ ├── model/ # Entidades JPA
│ │ ├── repository/ # Interfaces Spring Data JPA
│ │ ├── service/ # Lógica de negocio
│ │ ├── controller/ # Controladores REST
│ │ └── TestDataLoader.java # Datos de prueba
│ └── resources/
│ └── application.properties
└── test/
├── java/eu/cifpfbmoll/sportsclub/
│ ├── fixtures/ # Datos de prueba para tests
│ └── service/ # Tests unitarios
└── resources/
└── application-test.properties