# Parking Lot

- Java 17
- Gradle
- PostgreSQL
- OpenAPI v3.0
- JSON Web Token
- Spring Security v6.2.0
- Spring Boot Admin v3.1.8

### Swagger
obs: to access protected routes, need to signup and get token at login endpoint
http://localhost:8081/swagger-ui/index.html

### Unprotected Routes

| API ROUTE		     | ROLE REQUIRED		 | DESCRIPTION                  |
|:----------------|:----------------|:---------------------------------------|
| [POST] /auths   | ----------      | Signup new user and creates user token |


### Protected Routes

| API ROUTE		                           | ROLE REQUIRED		          | DESCRIPTION                             |
|:--------------------------------------|:-------------------------|:----------------------------------------|
| [POST] /admins                        | User, Admin, Super Admin | Create an administrator                 |
| [GET] /users/me                       | User, Admin, Super Admin | Retrieve the current authenticated user |
| [GET] /users	                         | Admin, Super Admin       | Retrieve the list of all the users      |
| [GET, POST, UPDATE, DELETE] /parking	 | User, Admin, Super Admin | Parking operations                      |


### Errors

| AUTHENTICATION ERROR	                 | EXCEPTION THROWN	              | HTTP STATUS CODE |
|:--------------------------------------|:-------------------------------|:----------------|
| Bad login credentials	              | BadCredentialsException        | 401           |
| Account locked	                      | AccountStatusException         | 403            |
| Not authorized to access a resource   | AccessDeniedException          | 403            |
| Invalid JWT		                     | SignatureException             | 401           |
| JWT has expired	                      | ExpiredJwtException            | 401           |

## Parking Endpoints

#### create a parking

```bash
curl -X 'POST' \
  'http://localhost:8081/parking' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0YW5kb0BlbWFpbC5jb20iLCJpYXQiOjE3MDEyNjcxODMsImV4cCI6MTcwMTI3MDc4M30.jM6PwoRQdGy9cyWTWbxER7LK7amI3EtPoBXNlWFlHoI' \
  -H 'Content-Type: application/json' \
  -d '{
  "license": "AAA 222",
  "state": "RS",
  "model": "Toyota A",
  "color": "BLUE"
}'
  ```
###### 201 CREATED

``` json
{
  "id": "9066e107de494e80a784517c2882ddaa",
  "license": "AAA 222",
  "state": "RS",
  "model": "Toyota A",
  "color": "BLUE",
  "entryDate": "29/11/2023 11:16"
}
```
#### create exit

```bash
curl -X 'POST' \
  'http://localhost:8081/parking/ed60d88ddf0a451cbc95d7cb34750559/exit' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0YW5kb0BlbWFpbC5jb20iLCJpYXQiOjE3MDEyNjcxODMsImV4cCI6MTcwMTI3MDc4M30.jM6PwoRQdGy9cyWTWbxER7LK7amI3EtPoBXNlWFlHoI' \
  -d ''
  ```
###### 200 OK

``` json
{
  "id": "ed60d88ddf0a451cbc95d7cb34750559",
  "license": "AAA 111",
  "state": "SP",
  "model": "Hyundai A",
  "color": "BLACK",
  "entryDate": "29/11/2023 11:15",
  "exitDate": "29/11/2023 11:15",
  "bill": 0
}
```
#### get all parkings

```bash
curl -X 'GET' \
  'http://localhost:8081/parking' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0YW5kb0BlbWFpbC5jb20iLCJpYXQiOjE3MDEyNjcxODMsImV4cCI6MTcwMTI3MDc4M30.jM6PwoRQdGy9cyWTWbxER7LK7amI3EtPoBXNlWFlHoI'
  ```
###### 200 OK

``` json
[
  {
    "id": "ed60d88ddf0a451cbc95d7cb34750559",
    "license": "AAA 111",
    "state": "SP",
    "model": "Hyundai A",
    "color": "BLACK",
    "entryDate": "29/11/2023 11:15",
    "exitDate": "29/11/2023 11:15",
    "bill": 0
  }
]
```
#### get a parking by its ID

```bash
curl -X 'GET' \
  'http://localhost:8081/parking/ed60d88ddf0a451cbc95d7cb34750559' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0YW5kb0BlbWFpbC5jb20iLCJpYXQiOjE3MDEyNjcxODMsImV4cCI6MTcwMTI3MDc4M30.jM6PwoRQdGy9cyWTWbxER7LK7amI3EtPoBXNlWFlHoI'
  ```
###### 200 OK

``` json
{
  "id": "ed60d88ddf0a451cbc95d7cb34750559",
  "license": "AAA 111",
  "state": "SP",
  "model": "Hyundai A",
  "color": "BLACK",
  "entryDate": "29/11/2023 11:15"
}
```

#### update parking information by its ID
[to be fixed] obs: even if you have to update few fields, it is necessary to fill all properties.
```bash
curl -X 'PUT' \
  'http://localhost:8081/parking/9066e107de494e80a784517c2882ddaa' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0YW5kb0BlbWFpbC5jb20iLCJpYXQiOjE3MDEyNjcxODMsImV4cCI6MTcwMTI3MDc4M30.jM6PwoRQdGy9cyWTWbxER7LK7amI3EtPoBXNlWFlHoI' \
  -H 'Content-Type: application/json' \
  -d '{
    "license": "AAA 111",
    "state": "MG",
    "model": "Toyota A",
    "color": "WHITE"
}'
  ```
###### 200 OK

``` json
{
  "id": "ed60d88ddf0a451cbc95d7cb34750559",
  "license": "AAA 111",
  "state": "SP",
  "model": "Hyundai A",
  "color": "BLACK",
  "entryDate": "29/11/2023 11:15"
}
```

#### delete parking by its ID
```bash
curl -X 'DELETE' \
  'http://localhost:8081/parking/9066e107de494e80a784517c2882ddaa' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0YW5kb0BlbWFpbC5jb20iLCJpYXQiOjE3MDEyNjcxODMsImV4cCI6MTcwMTI3MDc4M30.jM6PwoRQdGy9cyWTWbxER7LK7amI3EtPoBXNlWFlHoI'
  ```
###### 204 NO CONTENT

## Authentication Endpoints

#### register new user with default role

```bash
curl -X 'POST' \
  'http://localhost:8081/auth/signup' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "email": "teste1@email.com",
  "password": "123",
  "fullName": "Teste 1"
}'
  ```
###### 200 OK

``` json
{
  "id": 1,
  "fullName": "Teste 1",
  "email": "teste1@email.com",
  "password": "$2a$10$NnMpiaTa2WVAY8tB0MPAiuJGPRGljmxvXEF6lKg.n4O4l3yg4LLZy",
  "createdAt": "2023-11-29T17:55:46.296+00:00",
  "updatedAt": "2023-11-29T17:55:46.296+00:00",
  "role": {
    "id": 1,
    "name": "USER",
    "description": "Default user role",
    "createdAt": "2023-11-29T17:54:57.437+00:00",
    "updatedAt": "2023-11-29T17:54:57.437+00:00"
  },
  "enabled": true,
  "authorities": [
    {
      "authority": "ROLE_USER"
    }
  ],
  "username": "teste1@email.com",
  "accountNonExpired": true,
  "accountNonLocked": true,
  "credentialsNonExpired": true
}
```

#### create user token

```bash
curl -X 'POST' \
  'http://localhost:8081/auth/login' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "email": "teste1@email.com",
  "password": "123"
}'
  ```
###### 200 OK

``` json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZTFAZW1haWwuY29tIiwiaWF0IjoxNzAxMjgwNjE0LCJleHAiOjE3MDEyODQyMTR9.7B2uMRY1OFC05lzCRvdpK2AOXc3zHiLRef8-pOIj7n4",
  "expiresIn": 3600000
}
```

## Parking Endpoints

#### get the authenticated user

```bash
curl -X 'GET' \
  'http://localhost:8081/users/me' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZTFAZW1haWwuY29tIiwiaWF0IjoxNzAxMjgwNjE0LCJleHAiOjE3MDEyODQyMTR9.7B2uMRY1OFC05lzCRvdpK2AOXc3zHiLRef8-pOIj7n4'
  ```
###### 200 OK

``` json
{
  "id": 1,
  "fullName": "Teste 1",
  "email": "teste1@email.com",
  "password": "$2a$10$NnMpiaTa2WVAY8tB0MPAiuJGPRGljmxvXEF6lKg.n4O4l3yg4LLZy",
  "createdAt": "2023-11-29T17:55:46.296+00:00",
  "updatedAt": "2023-11-29T17:55:46.296+00:00",
  "role": {
    "id": 1,
    "name": "USER",
    "description": "Default user role",
    "createdAt": "2023-11-29T17:54:57.437+00:00",
    "updatedAt": "2023-11-29T17:54:57.437+00:00"
  },
  "enabled": true,
  "authorities": [
    {
      "authority": "ROLE_USER"
    }
  ],
  "username": "teste1@email.com",
  "accountNonExpired": true,
  "accountNonLocked": true,
  "credentialsNonExpired": true
}
```

## Admin endpoints

Create your token at login endpoint to make these tests:
- [admin] 

| Parameter     | Value            | Description           |
|:--------------|:-----------------|:----------------------|
| `email`   | `teste5@email.com`   | **Required**    |
| `password` | `123`               | **Required**    |

- [super admin]

| Parameter     | Value            | Description           |
|:--------------|:-----------------|:----------------------|
| `email`   | `super.admin@email.com`   | **Required**    |
| `password` | `123456`               | **Required**    |

#### get the list of all the users
obs: Admin and Super Admin roles only
```bash
curl -X 'GET' \
  'http://localhost:8081/users' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZTNAZW1haWwuY29tIiwiaWF0IjoxNzAxMjgxOTkwLCJleHAiOjE3MDEyODU1OTB9.jKBuNlhZlVqWn2tGDyqawNvqKswWbC3ZCFtRvgc-ODg'
  ```
###### 200 OK

``` json
[
  {
    "id": 1,
    "fullName": "Teste 1",
    "email": "teste1@email.com",
    "password": "$2a$10$UBkYG6a2JMm/RJrQeQ9e4eH6h2qD7jzQ8b0LwpuWFR0F4hqwcLb8q",
    "createdAt": "2023-11-29T18:04:04.168+00:00",
    "updatedAt": "2023-11-29T18:04:04.168+00:00",
    "role": {
      "id": 1,
      "name": "USER",
      "description": "Default user role",
      "createdAt": "2023-11-29T18:03:51.901+00:00",
      "updatedAt": "2023-11-29T18:03:51.901+00:00"
    },
    "enabled": true,
    "authorities": [
      {
        "authority": "ROLE_USER"
      }
    ],
    "username": "teste1@email.com",
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true
  },
  {
    "id": 2,
    "fullName": "Teste 2",
    "email": "teste2@email.com",
    "password": "$2a$10$gZCgkcMNLkU5Ldv7VS81quc7pohl8JHXQL3rPK159XU/rVcfTFXDm",
    "createdAt": "2023-11-29T18:04:13.671+00:00",
    "updatedAt": "2023-11-29T18:04:13.671+00:00",
    "role": {
      "id": 1,
      "name": "USER",
      "description": "Default user role",
      "createdAt": "2023-11-29T18:03:51.901+00:00",
      "updatedAt": "2023-11-29T18:03:51.901+00:00"
    },
    "enabled": true,
    "authorities": [
      {
        "authority": "ROLE_USER"
      }
    ],
    "username": "teste2@email.com",
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true
  },
  {
    "id": 52,
    "fullName": "Super Admin",
    "email": "super.admin@email.com",
    "password": "$2a$10$N6/gMDOZ2e5AJVPEG0Djgu0OLd3K9Y5a.pDYEEh5OSxvk7wtdqIR6",
    "createdAt": "2023-11-29T18:10:22.476+00:00",
    "updatedAt": "2023-11-29T18:10:22.476+00:00",
    "role": {
      "id": 3,
      "name": "SUPER_ADMIN",
      "description": "Super Administrator role",
      "createdAt": "2023-11-29T18:03:51.919+00:00",
      "updatedAt": "2023-11-29T18:03:51.919+00:00"
    },
    "enabled": true,
    "authorities": [
      {
        "authority": "ROLE_SUPER_ADMIN"
      }
    ],
    "username": "super.admin@email.com",
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true
  },
  {
    "id": 3,
    "fullName": "Teste 3",
    "email": "teste3@email.com",
    "password": "$2a$10$35t46.v11QsTZ.n9hKsa9ukgT4i5CLa8zxp0qPHZM624OY/8uFObG",
    "createdAt": "2023-11-29T18:04:24.201+00:00",
    "updatedAt": "2023-11-29T18:04:24.201+00:00",
    "role": {
      "id": 2,
      "name": "ADMIN",
      "description": "Administrator role",
      "createdAt": "2023-11-29T18:03:51.914+00:00",
      "updatedAt": "2023-11-29T18:03:51.914+00:00"
    },
    "enabled": true,
    "authorities": [
      {
        "authority": "ROLE_ADMIN"
      }
    ],
    "username": "teste3@email.com",
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true
  },
  {
    "id": 55,
    "fullName": "Teste 4",
    "email": "teste4@email.com",
    "password": "$2a$10$yhN/AFHbe7ktkHKPX/OHpubxRKvKgCdOqjGxUTw81k6qZO52PEwRO",
    "createdAt": "2023-11-29T18:26:06.367+00:00",
    "updatedAt": "2023-11-29T18:26:06.367+00:00",
    "role": {
      "id": 1,
      "name": "USER",
      "description": "Default user role",
      "createdAt": "2023-11-29T18:03:51.901+00:00",
      "updatedAt": "2023-11-29T18:03:51.901+00:00"
    },
    "enabled": true,
    "authorities": [
      {
        "authority": "ROLE_USER"
      }
    ],
    "username": "teste4@email.com",
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true
  },
  {
    "id": 57,
    "fullName": "Teste 5",
    "email": "teste5@email.com",
    "password": "$2a$10$xQAvt/FUejpFkaKB3.Oh1.8kPfOvmG2Vm9lx4fSW1MmcSL1fvMFgS",
    "createdAt": "2023-11-29T18:27:05.441+00:00",
    "updatedAt": "2023-11-29T18:27:05.441+00:00",
    "role": {
      "id": 2,
      "name": "ADMIN",
      "description": "Administrator role",
      "createdAt": "2023-11-29T18:03:51.914+00:00",
      "updatedAt": "2023-11-29T18:03:51.914+00:00"
    },
    "enabled": true,
    "authorities": [
      {
        "authority": "ROLE_ADMIN"
      }
    ],
    "username": "teste5@email.com",
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true
  },
  {
    "id": 102,
    "fullName": "Teste 6",
    "email": "teste6@email.com",
    "password": "$2a$10$l0uw0h0I/65f9rQUoJqFge6IrVYiop/b7TCoUZdZXK3Z/qQT4/M9G",
    "createdAt": "2023-11-29T18:29:32.798+00:00",
    "updatedAt": "2023-11-29T18:29:32.798+00:00",
    "role": {
      "id": 2,
      "name": "ADMIN",
      "description": "Administrator role",
      "createdAt": "2023-11-29T18:03:51.914+00:00",
      "updatedAt": "2023-11-29T18:03:51.914+00:00"
    },
    "enabled": true,
    "authorities": [
      {
        "authority": "ROLE_ADMIN"
      }
    ],
    "username": "teste6@email.com",
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true
  }
]
```

#### add new user as Admin 
obs: Super Admin role only
```bash
curl -X 'POST' \
  'http://localhost:8081/admins' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXBlci5hZG1pbkBlbWFpbC5jb20iLCJpYXQiOjE3MDEyODIyOTMsImV4cCI6MTcwMTI4NTg5M30.Qtr8fcUlIhFKyI3ugMxC7lnSVdp30eCGizeBUKgtoHw' \
  -H 'Content-Type: application/json' \
  -d '{
  "email": "teste6@email.com",
  "password": "123",
  "fullName": "Teste 6"
}'
  ```
###### 200 OK

``` json
{
  "id": 102,
  "fullName": "Teste 6",
  "email": "teste6@email.com",
  "password": "$2a$10$l0uw0h0I/65f9rQUoJqFge6IrVYiop/b7TCoUZdZXK3Z/qQT4/M9G",
  "createdAt": "2023-11-29T18:29:32.798+00:00",
  "updatedAt": "2023-11-29T18:29:32.798+00:00",
  "role": {
    "id": 2,
    "name": "ADMIN",
    "description": "Administrator role",
    "createdAt": "2023-11-29T18:03:51.914+00:00",
    "updatedAt": "2023-11-29T18:03:51.914+00:00"
  },
  "enabled": true,
  "authorities": [
    {
      "authority": "ROLE_ADMIN"
    }
  ],
  "username": "teste6@email.com",
  "accountNonExpired": true,
  "accountNonLocked": true,
  "credentialsNonExpired": true
}
```