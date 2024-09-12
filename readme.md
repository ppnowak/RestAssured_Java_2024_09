# Prezentacja

Prezentacja znajduje się pod tym linkiem: https://api.breathtaking.website/files/prezentacja.pdf

# RestAssured vs API paths

Example path:
```
GET https://api.prod/api/v1/users
```

## Approach #1
`given().when().get("https://api.prod/api/v1/users").then()...`

Problem:
- when we change the URL - all tests must be edited

## Approach #2
```java
given()
    .baseUri("https://api.prod")
    .basePath("api/v1")
.when().get("/users")
.then()...
```

## Approach #3
```java
given()
    .baseUri(Config.BASE_URI)
    .basePath(Config.BASE_PATH)
.when().get("/users")
.then()...
```

# RestAssured vs Path Parameters

## Approach #1
```java
var userId = 123;
given()
    .baseUri(Config.BASE_URI)
    .basePath(Config.BASE_PATH)
.when().get("/users/" + userId)
.then()...
```

## Approach #1
```java
given()
    .baseUri(Config.BASE_URI)
    .basePath(Config.BASE_PATH)
    .pathParam("userId", 123)
.when().get("/users/{userId}")
.then()...
```

# Gateways

Main application has HTTP endpoint:
```
GET /users/:userId
PUT /users/:userId
```

When mobile app is created, gateway for user read is created as well
```
GET /me ( ->>> GET /users/:userId)
```
