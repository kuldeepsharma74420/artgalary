# Art Gallery - AI-Powered Art Generation Platform

A full-stack web application that generates AI art using Pollination AI and stores it in a gallery format.

## Tech Stack

**Backend:**
- Spring Boot 3.x
- MySQL Database
- Maven
- Pollination AI Integration

**Frontend:**
- Angular 19
- TypeScript
- SCSS

## Prerequisites

- Java 17 or higher
- Node.js 18+ and npm
- MySQL 8.0+
- Maven 3.6+

## Setup Instructions

### 1. Clone the Repository
```bash
git clone <your-repo-url>
cd ArtGallery
```

### 2. Database Setup
```sql
CREATE DATABASE art_gallery;
```

### 3. Backend Setup

1. Navigate to backend directory:
```bash
cd backend
```

2. Create `application.properties` from template:
```bash
cp src/main/resources/application-template.properties src/main/resources/application.properties
```

3. Update `application.properties` with your credentials:
- Database username/password
- Pollination AI API key

4. Run the backend:
```bash
./mvnw spring-boot:run
```

Backend will start on `http://localhost:8080`

### 4. Frontend Setup

1. Navigate to frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start development server:
```bash
ng serve
```

Frontend will start on `http://localhost:4200`

## Features

- AI art generation using text prompts
- Gallery view of generated artwork
- Image download functionality
- Responsive design

## API Endpoints

- `POST /api/art/generate` - Generate new art
- `GET /api/art/all` - Get all generated art
- `GET /api/art/download/{id}` - Download specific artwork

## Project Structure

```
ArtGallery/
├── backend/                 # Spring Boot application
│   ├── src/main/java/      # Java source code
│   ├── src/main/resources/ # Configuration files
│   └── pom.xml            # Maven dependencies
├── frontend/               # Angular application
│   ├── src/app/           # Angular components and services
│   ├── package.json       # Node dependencies
│   └── angular.json       # Angular configuration
└── README.md
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request