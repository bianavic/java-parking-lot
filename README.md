# Parking Lot

- Java 17
- Gradle
- PostgreSQL
- OpenAPI v3.0
- JSON Web Token
- Spring Security v6.2.0
- Spring Boot Admin v3.1.8

### Swagger
http://localhost:8081/swagger-ui/index.html

### Protected Routes

| API ROUTE		        | ROLE REQUIRED		           | DESCRIPTION                             |
|:-------------------|:--------------------------|:----------------------------------------|
| [POST] /admins     | User, Admin, Super Admin  | Create an administrator                 |
| [GET] /users/me    | Admin, Super Admin        | Retrieve the current authenticated user |
| [GET] /users	      | Super Admin                 | Retrieve the list of all the users      |

### Errors

| AUTHENTICATION ERROR	                 | EXCEPTION THROWN	              | HTTP STATUS CODE |
|:--------------------------------------|:-------------------------------|:----------------|
| Bad login credentials	              | BadCredentialsException        | 401           |
| Account locked	                      | AccountStatusException         | 403            |
| Not authorized to access a resource   | AccessDeniedException          | 403            |
| Invalid JWT		                     | SignatureException             | 401           |
| JWT has expired	                      | ExpiredJwtException            | 401           |
