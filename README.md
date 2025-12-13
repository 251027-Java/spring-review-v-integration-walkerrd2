# Lab: Complete Library System - Integration

## Goal
Complete the Library Management System with full CRUD operations, search functionality, and API documentation.

## Learning Objectives
- Implement derived query methods
- Add Swagger/OpenAPI documentation
- Create integration tests
- Add search and filtering capabilities

## Pre-requisites
- Completed 2304-6-1 through 2304-6-4

## Tasks

### Task 1: Add Search Endpoints
Add these search methods to BookRepository:
- `findByTitleContainingIgnoreCase(String title)`
- `findByAuthorContainingIgnoreCase(String author)`
- `findByAvailable(boolean available)`
- `findByIsbn(String isbn)`

Add corresponding controller endpoints:
- GET /api/books/search?title=xxx
- GET /api/books/search?author=xxx
- GET /api/books/available

### Task 2: Add Swagger Documentation
1. Add OpenAPI dependency:
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
```

2. Access Swagger UI at: `http://localhost:8080/swagger-ui.html`

3. Add API documentation annotations:
```java
@Operation(summary = "Get all books")
@ApiResponse(responseCode = "200", description = "List of books")
```

### Task 3: Create Integration Tests
Create `BookControllerTest.java` using `@SpringBootTest` and `@AutoConfigureMockMvc`:
- Test GET /api/books
- Test POST /api/books
- Test GET /api/books/{id} with valid and invalid IDs
- Test checkout and return operations

**Starter code provided**: See `starter_code/BookControllerTest.java`

### Task 4: Add Patron Endpoints
Create full CRUD for patrons:
- GET /api/patrons
- GET /api/patrons/{id}
- POST /api/patrons
- PUT /api/patrons/{id}
- DELETE /api/patrons/{id}

### Task 5: Add Statistics Endpoint
Create GET /api/stats that returns:
```json
{
  "totalBooks": 100,
  "availableBooks": 75,
  "totalPatrons": 50,
  "activeLoans": 25
}
```

## Deliverables
1. Search endpoints working
2. Swagger UI accessible
3. Integration tests passing
4. Statistics endpoint
5. Complete API documentation

## Verification
- [ ] Search by title works
- [ ] Search by author works
- [ ] Swagger UI shows all endpoints
- [ ] All tests pass
- [ ] Statistics endpoint returns correct counts

## Starter Code
See `starter_code/` directory
