# YFU Ideas Board

A service that allows users to post Ideas and share them with the entire organisations. Other people can comment on them, like them and even commit to them. Once you are ready, change the idea's state and start collaborating on it. 

## Setup 

### Dependencies
The service is built with Spring Boot so you'll need Java and Maven installed. It needs to connect to a Postgres database.

### Running the service

Once you got all dependencies installed, run the following commands to start the service on Port 8080.
```
git pull git@github.com:MethodenDB-YFU/ideas-service.git
cd ideas-service

mvn spring-boot:run
```

## Documentation
* There are no tests just yet. 
* A Swagger file is provided in the resources folder. 
* If you need assistance, open an issue. 