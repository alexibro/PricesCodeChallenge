[![Build Status](https://travis-ci.com/alexibro/CodeChallenge.svg?branch=master)](https://travis-ci.com/alexibro/PricesCodeChallenge)

# Code Challenge: Prices

## How to run

In the project's directory:
```
docker-compose up
```

## Default host:

http://localhost:8080

## API documentation:

Swagger docs: http://localhost:8080/swagger-ui.html#/

### Get Price:

* **Get price:**

    > GET /price/{productId}?brandId={brandId}&date={date}
    
## Plugins

### Lombok

Lombok plugin has beed used it in order to keep the code clean. It is necessary to install Lombok or the IDE will detect errors.

[![IntellijLombok](https://img.shields.io/badge/Extension-IntelliJ%20Lombok-yellowgreen.svg)](https://projectlombok.org/setup/intellij)

[![VSCodeLombok](https://img.shields.io/badge/Extension-VSCode%20Lombok-yellowgreen.svg)](https://projectlombok.org/setup/vscode)
    
## Database:

I have use **H2 embedded database** to persist data.

Console is enabled. You can access with following credentials:
    
   * **Username:** user

   * **Password:** pass
   
## Tests

### Integration tests

Integration tests have been implemented.

![Alt text](https://github.com/alexibro/PricesCodeChallenge/blob/master/resources/test-results.PNG)

### Postman

A **Postman collection has been provided** (resource folder) to execute requests.

![Alt text](https://github.com/alexibro/PricesCodeChallenge/blob/master/resources/postman-tests.PNG)

### CI

Travis file has been included, which passes the tests and builds the image.
(and uploads them to the Docker Hub uncommenting the .travis.yml lines). 

## Architecture

The requirements have been kept simple but structure has been implemented thinking about future application growth.

Two implementations of the getPrices method have been provided at the service layer:

* Recovering all the prices of the database and applying a filtering in the service, transferring all the business logic to this layer, leaving the repository as a "simple" connector to the database

* Using the names of the methods provided by Spring Data to query the database and directly obtain the expected result

The implementation has been carried out keeping the focus on SOLID principles throughout the development.

## Deployment:

### Multi-stage Dockerfile

"Transactions App" has a **multi-stage Dockerfile**:

* **Builds the application JAR**
* **Executes the JAR and expose it**

### Docker-compose

* **prices service:** Builds image from Dockerfile and exposes it. 
It uses H2 embedded database.

* **Default exposed port** is 8080. You can change it in docker-compose.yml file.

* **Important:** If you execute docker-compose and after that you make changes and execute docker again, a local image 
will be cached from the previous run and it won't detect changes automatically. In this case, use:

```
docker-compose up --build --force-recreate --no-deps [-d] [<service_name>..]
```

## Swagger

Swagger has been configured to document the API and facilitate the execution of test requests

Swagger docs: http://localhost:8080/swagger-ui.html#/
