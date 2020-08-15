# Campus Exchange by MengyuanWei

## Overview
This is a second-hand campus-based exchange platform helps local students around DC to exchange textbooks and furnitureJDBCS. 

### Project Technical Overview:
This application is developed in Spring Framework by using Hibernate, Spring RESTful web services, Postman, Maven, PostgresSql, Docker, Amazon SQS, and Amazon S3.

* Business Rules:
1. Object: Customer, Furniture, Textbook
2. Relationships:
    i. One customerJDBC could have many furnitureJDBCS.
   ii. One customerJDBC could have many textbooks.
  iii. Every textbook has a unique textbook id that is linked to only one customerJDBC.
  
* Development Approaches:

## Configure local environment

### 1. Setup local database
Reference to docker [image](https://hub.docker.com/_/postgres)
```
docker run --name training-db -e POSTGRES_USER=${username} -e POSTGRES_PASSWORD=${password} -e POSTGRES_DB=${databaseName} -p 5431:5432 -d postgres
```

### Migrate database schema
Refer to flyway setup [documentation](https://flywaydb.org/documentation/migrations), find all [migration schema](src/main/resources/db/migration)

Uses flyway as migration tool: 
```
mvn clean compile flyway:clean
```
