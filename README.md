# About

A simple Java project built with the Spring Boot framework. 

The project exposes some APIs that can be consulted through a Swagger documentation at http://localhost:8080/swagger-ui.html and makes use of H2 in-memory database (check data.sql and schema.sql files).
  
# Signup and Login

To call the portal-nba-api you must signup and login to the application and obtain an authorization token (json web token).

### Signup

* Signup a new user with the given username

`/user-api/sign-up/{username} [POST]`

* Response sample:

```json
{
  "serviceOutcome": {
    "additionalInfo": "OK",
    "httpResponseCode": 200
  }
}
 ```
### Login

* Login to the application

`/user-api/login/{username} [GET]`

* Response sample:

```json
{
  "authToken": "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDM3NDQyNDEsInN1YiI6ImRhbWFyIiwiaXNzIjoiZGFtYXIiLCJleHAiOjE2MDM3NDQ1NDF9.-dqfY-DNXSVzC5cPvYHDzjq8A6tNWZ98Ww6keTQEm2o",
  "serviceOutcome": {
    "additionalInfo": "OK",
    "httpResponseCode": 200
  }
}
 ```

# References

Further references about the Spring modules and other project libraries:

[Spring-Data](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference)

[Spring-AOP](https://docs.spring.io/spring/docs/2.5.x/reference/aop.html)

[JWT](https://jwt.io/)

[Dozer](https://dozermapper.github.io/)

[Lombok](https://projectlombok.org/)


