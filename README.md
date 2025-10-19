# Ad Event Tracking System

A distributed real-time ad event tracking system built with Spring Boot and Apache Kafka. This application demonstrates event-driven architecture for capturing, streaming, and analyzing advertisement interaction data.

## 🏗️ Architecture

The system consists of two Spring Boot microservices:

- **Frontend Service** (Port 8081): Web interface for generating ad events and data visualization
- **Backend Service** (Port 8080): Consumer service that processes Kafka messages and provides REST APIs

```
┌─────────────┐    Kafka Topics     ┌─────────────┐    MySQL    ┌─────────────┐
│   Frontend  │ ──────────────────► │   Backend   │ ──────────► │  Database   │
│  (Producer) │   ad_viewed         │ (Consumer)  │             │             │
│             │   ad_clicked        │             │             │             │
│             │   ad_closed         │             │             │             │
└─────────────┘                     └─────────────┘             └─────────────┘
```

## 🚀 Features

- **Real-time Event Streaming**: Kafka-based message processing for ad interactions
- **Web Interface**: Simple UI for generating test events (ad viewed, clicked, closed)
- **Data Visualization**: Charts showing event distribution and trends
- **REST API**: Endpoints for accessing event data and statistics
- **Persistent Storage**: MySQL database for event history
- **Containerized Deployment**: Docker support with Kubernetes manifests

## 🛠️ Technology Stack

- **Java 17** - Programming language
- **Spring Boot 3.5.5** - Application framework
- **Apache Kafka** - Event streaming platform
- **MySQL** - Database for event persistence
- **Thymeleaf** - Template engine for web UI
- **Maven** - Build and dependency management
- **Docker** - Containerization
- **Kubernetes** - Container orchestration

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- Apache Kafka (local or remote)
- MySQL 8.0+
- Docker (optional, for containerized deployment)

## 🏃‍♂️ Quick Start

### 1. Clone the Repository
```bash
git clone <repository-url>
cd ad-event-tracking-system
```

### 2. Start Infrastructure Services

**Option A: Local Setup**
- Start Kafka on `localhost:9092`
- Start MySQL on `localhost:3306` with database `ads_db`

**Option B: Docker Compose** (if available)
```bash
docker-compose up -d kafka mysql
```

### 3. Configure Database
Create MySQL database and user:
```sql
CREATE DATABASE ads_db;
CREATE USER 'ads_user'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON ads_db.* TO 'ads_user'@'%';
```

### 4. Run Applications

**Backend Service:**
```bash
cd backend
./mvnw spring-boot:run
```

**Frontend Service:**
```bash
cd frontend  
./mvnw spring-boot:run
```

### 5. Access the Application
- Frontend UI: http://localhost:8081
- Backend API: http://localhost:8080/api/events
- Event Counts API: http://localhost:8080/api/events/counts

## 🔧 Configuration

### Environment Variables

**Backend Service:**
```bash
SPRING_KAFKA_BOOTSTRAP_SERVERS=localhost:9092
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/ads_db
SPRING_DATASOURCE_USERNAME=ads_user
SPRING_DATASOURCE_PASSWORD=password
```

**Frontend Service:**
```bash
KAFKA_BOOTSTRAP_SERVERS=localhost:9092
BACKEND_API_URL=http://localhost:8080/api/events
SERVER_PORT=8081
```

## 🐳 Docker Deployment

### Build Images
```bash
# Build backend image
cd backend
docker build -t ad-tracker-backend .

# Build frontend image  
cd frontend
docker build -t ad-tracker-frontend .
```

### Run with Docker
```bash
# Run backend
docker run -p 8080:8080 \
  -e SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/ads_db \
  ad-tracker-backend

# Run frontend
docker run -p 8081:8081 \
  -e KAFKA_BOOTSTRAP_SERVERS=kafka:9092 \
  -e BACKEND_API_URL=http://backend:8080/api/events \
  ad-tracker-frontend
```

## ☸️ Kubernetes Deployment

Deploy to Kubernetes using the provided manifests:

```bash
# Deploy backend
kubectl apply -f backend/deploy.yaml

# Deploy frontend
kubectl apply -f frontend/deploy.yaml
```

Update the deployment files with your specific:
- Container registry URLs
- Database connection strings
- Kafka bootstrap servers

## 📊 API Endpoints

### Backend Service (Port 8080)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/events` | Get all events |
| GET | `/api/events/counts` | Get event counts by type |

### Frontend Service (Port 8081)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Main event generation page |
| POST | `/send-event` | Send ad event to Kafka |
| GET | `/chart` | Event visualization page |
| GET | `/data` | Raw event data page |

## 🧪 Testing

### Run Unit Tests
```bash
# Backend tests
cd backend && ./mvnw test

# Frontend tests  
cd frontend && ./mvnw test
```

### Manual Testing
1. Access the frontend at http://localhost:8081
2. Click buttons to generate ad events (viewed, clicked, closed)
3. View real-time data at `/chart` and `/data` endpoints
4. Check backend API at http://localhost:8080/api/events

## 📁 Project Structure

```
├── backend/                 # Consumer service
│   ├── src/main/java/
│   │   └── com/example/backend/
│   │       ├── controller/  # REST controllers
│   │       ├── entity/      # JPA entities
│   │       ├── repository/  # Data access layer
│   │       └── service/     # Business logic
│   └── Dockerfile
├── frontend/                # Producer service & web UI
│   ├── src/main/java/
│   │   └── com/example/frontend/
│   │       ├── controller/  # Web controllers
│   │       ├── model/       # Data models
│   │       └── service/     # Kafka producers
│   ├── src/main/resources/templates/  # Thymeleaf templates
│   └── Dockerfile
└── README.md
```

## 🔍 Monitoring

- **Application Logs**: Check console output for Kafka message processing
- **Database**: Query `ad_event` table for stored events
- **Kafka Topics**: Monitor `ad_viewed`, `ad_clicked`, `ad_closed` topics

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Troubleshooting

**Common Issues:**

1. **Kafka Connection Failed**: Ensure Kafka is running and accessible
2. **Database Connection Error**: Verify MySQL is running and credentials are correct
3. **Port Already in Use**: Check if ports 8080/8081 are available
4. **Maven Build Fails**: Ensure Java 17+ is installed and JAVA_HOME is set

**Logs Location:**
- Application logs: Console output
- Kafka logs: Check Kafka server logs
- Database logs: MySQL error logs