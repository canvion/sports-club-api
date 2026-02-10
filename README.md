# Sports Club API

## Descripción

API REST desarrollada con Spring Boot que permite gestionar atletas, entrenadores y actividades de un club deportivo. El proyecto incluye integración continua automatizada mediante GitHub Actions, contenedorización con Docker y una suite completa de tests unitarios, de integración y de aceptación.

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

```
src/
├── main/
│   ├── java/eu/cifpfbmoll/sportsclub/
│   │   ├── model/              # Entidades JPA
│   │   ├── repository/         # Interfaces Spring Data JPA
│   │   ├── service/            # Lógica de negocio
│   │   ├── controller/         # Controladores REST
│   │   └── TestDataLoader.java # Datos de prueba
│   └── resources/
│       └── application.properties
└── test/
    ├── java/eu/cifpfbmoll/sportsclub/
    │   ├── fixtures/           # Datos de prueba para tests
    │   ├── service/            # Tests de integración
    │   └── controller/         # Tests de aceptación
    └── resources/
        └── application-test.properties
```

## Puesta en marcha

### Prerrequisitos

- Docker y Docker Compose instalados
- Java 21 (solo para desarrollo local)
- Maven 3.9+ (solo para desarrollo local)

### Levantar el proyecto con Docker

```bash
# Construir y levantar los contenedores
docker-compose up -d

# Ver logs de la aplicación
docker-compose logs -f app

# Verificar que está funcionando
curl http://localhost:8080/api/athletes
```

### Parar el proyecto

```bash
# Detener contenedores
docker-compose down

# Para eliminar también los volúmenes (datos de la BD)
docker-compose down -v
```

### Desarrollo local (sin Docker)

```bash
# Compilar el proyecto
./mvnw clean install

# Ejecutar la aplicación
./mvnw spring-boot:run

# La aplicación estará disponible en http://localhost:8080
```

## Cargar datos de prueba (fixtures)

Los datos de prueba se cargan **automáticamente** al iniciar la aplicación gracias a `TestDataLoader.java`.

Este loader crea:
- **3 Coaches** (entrenadores)
- **10 Athletes** (atletas)
- **10 Activities** (actividades de entrenamiento y competición)

Los fixtures solo se cargan cuando la aplicación **NO** está ejecutando en perfil `test`. Es decir:
-  Se cargan en desarrollo (`docker-compose up`)
-  Se cargan con `./mvnw spring-boot:run`
-  NO se cargan durante la ejecución de tests
- 
## Ejecutar tests

### Tests unitarios

Los tests unitarios son tests básicos que prueban funcionalidades individuales sin necesidad de base de datos.

```bash
./mvnw test -Dtest="**/fixtures/*Test.java" -DfailIfNoTests=false
```

### Tests de integración

Los tests de integración verifican la integración con la base de datos PostgreSQL y Spring Boot.

```bash
./mvnw test -Dtest="**/*ServiceTest.java"
```

Estos tests incluyen:
- `AthleteServiceTest` - Pruebas del servicio de atletas
- `CoachServiceTest` - Pruebas del servicio de entrenadores
- `ActivityServiceTest` - Pruebas del servicio de actividades

### Tests de aceptación

Los tests de aceptación son tests end-to-end que verifican los endpoints HTTP completos de la API.

```bash
./mvnw test -Dtest="**/*AcceptanceTest.java"
```

Estos tests incluyen:
- `AthleteControllerAcceptanceTest` - Tests HTTP de endpoints de atletas
- `CoachControllerAcceptanceTest` - Tests HTTP de endpoints de entrenadores
- `ActivityControllerAcceptanceTest` - Tests HTTP de endpoints de actividades

### Ejecutar todos los tests

```bash
./mvnw test
```

## Verificar funcionamiento con curl

Una vez la aplicación está en marcha, puedes probar los endpoints con estos ejemplos:

### Athletes (Atletas)

#### Obtener todos los atletas
```bash
curl http://localhost:8080/api/athletes
```

#### Obtener atleta por ID
```bash
curl http://localhost:8080/api/athletes/1
```

#### Crear nuevo atleta
```bash
curl -X POST http://localhost:8080/api/athletes \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John",
    "surname": "Doe",
    "email": "john.doe@athlete.com",
    "birthDate": "2005-05-15"
  }'
```

#### Actualizar atleta existente
```bash
curl -X PUT http://localhost:8080/api/athletes/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John",
    "surname": "Updated",
    "email": "john.updated@athlete.com",
    "birthDate": "2005-05-15"
  }'
```

#### Eliminar atleta
```bash
curl -X DELETE http://localhost:8080/api/athletes/1
```

#### Buscar atleta por email
```bash
curl http://localhost:8080/api/athletes/email/alice.brown@athlete.com
```

### Coaches (Entrenadores)

#### Obtener todos los entrenadores
```bash
curl http://localhost:8080/api/coaches
```

#### Obtener entrenador por ID
```bash
curl http://localhost:8080/api/coaches/1
```

#### Crear nuevo entrenador
```bash
curl -X POST http://localhost:8080/api/coaches \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jane",
    "surname": "Coach",
    "email": "jane.coach@club.com",
    "birthDate": "1980-03-20",
    "certification": "Advanced Level"
  }'
```

#### Actualizar entrenador
```bash
curl -X PUT http://localhost:8080/api/coaches/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John",
    "surname": "Smith",
    "email": "john.smith@club.com",
    "birthDate": "1980-05-15",
    "certification": "UEFA Pro License Updated"
  }'
```

#### Eliminar entrenador
```bash
curl -X DELETE http://localhost:8080/api/coaches/1
```

### Activities (Actividades)

#### Obtener todas las actividades
```bash
curl http://localhost:8080/api/activities
```

#### Obtener actividad por ID
```bash
curl http://localhost:8080/api/activities/1
```

#### Obtener actividades por tipo
```bash
# Solo entrenamientos
curl http://localhost:8080/api/activities/type/TRAINING

# Solo competiciones
curl http://localhost:8080/api/activities/type/COMPETITION
```

#### Crear nueva actividad
```bash
curl -X POST http://localhost:8080/api/activities \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Evening Training",
    "type": "TRAINING",
    "dateTime": "2026-03-10T18:00:00",
    "durationMinutes": 90,
    "location": "Training Ground"
  }'
```

#### Actualizar actividad
```bash
curl -X PUT http://localhost:8080/api/activities/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Morning Training Session Updated",
    "type": "TRAINING",
    "dateTime": "2026-02-01T09:00:00",
    "durationMinutes": 120,
    "location": "Main Stadium Track"
  }'
```

#### Eliminar actividad
```bash
curl -X DELETE http://localhost:8080/api/activities/1
```

## Tests del proyecto

El proyecto incluye **3 tipos de tests** que verifican diferentes aspectos de la aplicación.

### Resumen de tests

- **Tests Unitarios**: 0 tests (fixtures básicos)
- **Tests de Integración**: 20 tests
- **Tests de Aceptación**: 9 tests

---

### Tests de Integración (20 tests)

Los tests de integración verifican la lógica de negocio y la integración con la base de datos PostgreSQL.

#### `AthleteServiceTest.java` (8 tests)

Pruebas del servicio de gestión de atletas:

1. `testCreateAthlete()` - Crear un nuevo atleta
2. `testFindAthleteById()` - Buscar atleta por ID
3. `testFindAllAthletes()` - Obtener todos los atletas
4. `testUpdateAthlete()` - Actualizar datos de un atleta
5. `testDeleteAthlete()` - Eliminar un atleta
6. `testFindByEmail()` - Buscar atleta por email
7. `testFindByEmailNotFound()` - Verificar que email no existe
8. `testAthleteEmailIsUnique()` - Validar que el email es único

#### `CoachServiceTest.java` (6 tests)

Pruebas del servicio de gestión de entrenadores:

1. `testCreateCoach()` - Crear un nuevo entrenador
2. `testFindCoachById()` - Buscar entrenador por ID
3. `testFindAllCoaches()` - Obtener todos los entrenadores
4. `testUpdateCoach()` - Actualizar datos de un entrenador
5. `testDeleteCoach()` - Eliminar un entrenador
6. `testFindByEmail()` - Buscar entrenador por email

#### `ActivityServiceTest.java` (6 tests)

Pruebas del servicio de gestión de actividades:

1. `testCreateActivity()` - Crear una nueva actividad
2. `testFindActivityById()` - Buscar actividad por ID
3. `testFindAllActivities()` - Obtener todas las actividades
4. `testFindByType()` - Filtrar actividades por tipo (TRAINING/COMPETITION)
5. `testUpdateActivity()` - Actualizar datos de una actividad
6. `testDeleteActivity()` - Eliminar una actividad

**Características de los tests de integración:**
- Usan `@SpringBootTest` para cargar el contexto completo
- Usan perfil `test` con base de datos H2 en memoria
- Usan `@Transactional` para hacer rollback después de cada test
- Verifican operaciones CRUD completas
- Prueban validaciones y constraints de la base de datos

---

### Tests de Aceptación (9 tests)

Los tests de aceptación son tests **end-to-end** que verifican los endpoints HTTP completos de la API REST.

#### `AthleteControllerAcceptanceTest.java` (3 tests)

Tests HTTP de los endpoints de atletas:

1. `testGetAllAthletes()` - `GET /api/athletes` - Obtener lista de atletas
2. `testGetAthleteById()` - `GET /api/athletes/{id}` - Obtener atleta específico
3. `testCreateAthlete()` - `POST /api/athletes` - Crear nuevo atleta

#### `CoachControllerAcceptanceTest.java` (3 tests)

Tests HTTP de los endpoints de entrenadores:

1. `testGetAllCoaches()` - `GET /api/coaches` - Obtener lista de entrenadores
2. `testGetCoachById()` - `GET /api/coaches/{id}` - Obtener entrenador específico
3. `testCreateCoach()` - `POST /api/coaches` - Crear nuevo entrenador

#### `ActivityControllerAcceptanceTest.java` (3 tests)

Tests HTTP de los endpoints de actividades:

1. `testGetAllActivities()` - `GET /api/activities` - Obtener lista de actividades
2. `testGetActivityById()` - `GET /api/activities/{id}` - Obtener actividad específica
3. `testCreateActivity()` - `POST /api/activities` - Crear nueva actividad

**Características de los tests de aceptación:**
- Usan `MockMvc` para simular peticiones HTTP reales
- Verifican códigos de estado HTTP (200, 201, 404, etc.)
- Prueban serialización/deserialización JSON
- Validan que los controladores REST funcionan correctamente
- Simulan el comportamiento de un cliente HTTP real

---

## GitHub Actions Workflow

### Descripción del Pipeline CI/CD

El workflow de integración continua se encuentra en `.github/workflows/ci.yml` y se ejecuta automáticamente en:

- Cada **push** a las ramas `main` y `develop`
- Cada **pull request** hacia la rama `main`

El pipeline garantiza que el código cumple con los estándares de calidad antes de ser integrado.

---

### Fases del Pipeline

El pipeline sigue las fases especificadas en el enunciado de la práctica:

#### 1. **Checkout code** (`actions/checkout@v4`)
- Descarga el código fuente del repositorio
- Permite acceder al código en los siguientes pasos

#### 2. **Set up JDK 21** (`actions/setup-java@v4`)
- Configura el entorno Java 21 con distribución Temurin
- Activa la caché de Maven para optimizar builds subsecuentes
- Reduce tiempo de ejecución al reutilizar dependencias

#### 3. **Code style check - Checkstyle** (Linting)
- Comando: `./mvnw checkstyle:check`
- Valida que el código cumple los estándares de estilo (Google Java Style)
- Verifica formato, convenciones de nombres, estructura del código
- Configurado con `continue-on-error: true` (no falla el build, solo advierte)

#### 4. **Run unit tests** (Tests Unitarios)
- Comando: `./mvnw test -Dtest="**/fixtures/*Test.java"`
- Ejecuta tests unitarios básicos
- Tests rápidos que no requieren base de datos
- Usa perfil `test` de Spring

#### 5. **Run integration tests** (Tests de Integración)
- Comando: `./mvnw test -Dtest="**/*ServiceTest.java"`
- Ejecuta tests de servicios con base de datos
- Usa PostgreSQL como servicio de GitHub Actions
- Verifica integración entre capas (service + repository + database)
- Incluye: AthleteServiceTest, CoachServiceTest, ActivityServiceTest

#### 6. **Build application**
- Comando: `./mvnw -B package -DskipTests`
- Compila el código y genera el archivo JAR
- Flag `-B` para modo batch (sin interacción)
- Salta tests porque ya se ejecutaron en pasos anteriores
- Genera `target/sportsclub-0.0.1-SNAPSHOT.jar`

#### 7. **Build Docker image**
- Comando: `docker build -t sportsclub-app:${{ github.sha }}`
- Construye la imagen Docker de la aplicación
- Tag con el SHA del commit para trazabilidad
- Usa multi-stage build para optimizar tamaño de la imagen
- Incluye tanto el JDK (builder) como JRE (runtime)

#### 8. **Run acceptance tests** (Tests de Aceptación)
- Comando: `./mvnw test -Dtest="**/*AcceptanceTest.java"`
- Ejecuta tests end-to-end de los endpoints HTTP
- Verifica que la API REST funciona correctamente
- Prueba serialización JSON y códigos de respuesta HTTP
- Incluye: AthleteControllerAcceptanceTest, CoachControllerAcceptanceTest, ActivityControllerAcceptanceTest

#### 9. **Upload test results**
- Usa: `actions/upload-artifact@v4`
- Sube informes de tests como artefactos de GitHub
- Se ejecuta **siempre** (`if: always()`), incluso si hay fallos
- Ubicación: `target/surefire-reports/`
- Permite descargar y revisar resultados detallados

---

### Servicios del Pipeline

El workflow usa **PostgreSQL** como servicio para los tests de integración:

```yaml
services:
  postgres:
    image: postgres:16-alpine
    env:
      POSTGRES_DB: sportsclub_test
      POSTGRES_USER: test
      POSTGRES_PASSWORD: test
    ports:
      - 5432:5432
    options: >-
      --health-cmd pg_isready
      --health-interval 10s
      --health-timeout 5s
      --health-retries 5
```

**Características:**
- Imagen Alpine (ligera)
- Base de datos dedicada para tests
- Healthcheck configurado para esperar disponibilidad
- Se ejecuta en paralelo al job principal

---

### Visualización del flujo

```
┌─────────────────────────────────────────────────────────────┐
│  1. Checkout code                                            │
└───────────────────────┬─────────────────────────────────────┘
                        ↓
┌─────────────────────────────────────────────────────────────┐
│  2. Setup JDK 21 + Maven cache                               │
└───────────────────────┬─────────────────────────────────────┘
                        ↓
┌─────────────────────────────────────────────────────────────┐
│  3. Checkstyle (linting)                                     │
└───────────────────────┬─────────────────────────────────────┘
                        ↓
┌─────────────────────────────────────────────────────────────┐
│  4. Unit Tests                                               │
└───────────────────────┬─────────────────────────────────────┘
                        ↓
┌─────────────────────────────────────────────────────────────┐
│  5. Integration Tests (con PostgreSQL)                      │
└───────────────────────┬─────────────────────────────────────┘
                        ↓
┌─────────────────────────────────────────────────────────────┐
│  6. Build Application (JAR)                                  │
└───────────────────────┬─────────────────────────────────────┘
                        ↓
┌─────────────────────────────────────────────────────────────┐
│  7. Build Docker Image                                       │
└───────────────────────┬─────────────────────────────────────┘
                        ↓
┌─────────────────────────────────────────────────────────────┐
│  8. Acceptance Tests (End-to-End)                           │
└───────────────────────┬─────────────────────────────────────┘
                        ↓
┌─────────────────────────────────────────────────────────────┐
│  9. Upload Test Results (artifacts)                         │
└─────────────────────────────────────────────────────────────┘
```

---

### Monitorización y resultados

Puedes ver el estado del pipeline en GitHub:

1. **En el repositorio**: El badge muestra el estado (passing/failing)
2. **Actions tab**: Ver logs detallados de cada ejecución
3. **Pull Requests**: El pipeline se ejecuta automáticamente y muestra checks
4. **Artifacts**: Descargar informes de tests en la página del workflow

---

## Tecnologías utilizadas

- **Java 21** - Lenguaje de programación
- **Spring Boot 4.0.2** - Framework de aplicación
- **Spring Data JPA** - Persistencia de datos
- **PostgreSQL 16** - Base de datos de producción
- **H2 Database** - Base de datos en memoria para tests
- **Maven** - Gestión de dependencias y build
- **JUnit 5** - Framework de testing
- **Docker** - Contenedorización
- **GitHub Actions** - CI/CD
- **Checkstyle** - Análisis de calidad de código