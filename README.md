# About

A simple Java project built with the Spring Boot framework, mainly based on Spring-Data, Spring-AOP and Spring-MVC modules. 

The project exposes some APIs that can be consulted through a Swagger documentation at http://localhost:8080/swagger-ui.html and makes use of H2 in-memory database (check data.sql and schema.sql files).

# Run

To run the application execute the following command:

  `mvn spring-boot:run`

(make sure you are inside the directory that contains the pom.xml file and don't forget to set up your JAVA_HOME and MAVEN_HOME environment variables).

You can also run the application following those steps:

- run the following command to generate the jar that wraps your application (spring-aop-data directory):

  `mvn clean install`
  
- run the following command to start your application (spring-aop-data/target directory):

  `java -jar spring-aop-data-0.0.1-SNAPSHOT.jar`
  
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

Further references about the Spring Data/AOP modules and other project libraries:

[Spring-Data](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference)

[Spring-AOP](https://docs.spring.io/spring/docs/2.5.x/reference/aop.html)

[JWT](https://jwt.io/)

[Dozer](https://dozermapper.github.io/)

[Lombok](https://projectlombok.org/)


