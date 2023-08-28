# Inditex technical interview test

## Target
Create an endpoint to consult products applicable prices.

## Design
Java 17 and Spring Boot 3.1.3, using the projects Spring Web, Spring Data, H2 in memory database and embedded Tomcat.
The design is basen on DDD using hexagonal architecture + vertical slicing. The main domain class is Price holding the
product price information. Also in the domain layer I have the internal port GetProductApplicablePrice which describes 
the use case and the external port PriceRepository which will be implemented in the infrastructure layer to connect with
the H2 DB. In the application layer stands the PriceService, a facade to the use cases (in this case just one).

## Relevant commands
### Building the project
It's a maven project so you can use the scripts (mvnw/mvnw.cmd) already present in the project or use your installed 
maven (mvn package).

### Run the project via command line
By default the service will run on port 8080.<br>
In the directory where the executable .jar is:<br>
`java -jar inditex-tech-test-1.0.0.jar` Add -Dserver.port=**portNumber** to change default port

Or in the project directory with maven and spring boot plugin:<br>
`mvn spring-boot:run`

### Run the project with Docker
Another option is to create a Docker container with the project. First [build the executable .jar](#building-the-project) and then 
run the following commands depending on your OS:
#### Mac/Linux
Create the image: `docker build -t inditex-tech-test .`<br>
Create the container: `docker run -p 8080:8080 --name inditexTestContainer inditex-tech-test`
#### Windows
Create the image: `docker build -t inditex-tech-test -f Dockerfile.windows .\`<br>
Create the container: `docker run -p 8080:8080 --name inditexTestContainer inditex-tech-test`

## Relevant URLs
* [Swagger](http://localhost:8080/swagger-ui/index.html)
* [H2 Console](http://localhost:8080/h2-console)
  * JDBC URL: jdbc:h2:mem:inditexdb
  * User name: sa
  * Password: 123456

## Postman collection
In the project root I've added a postman collection (**Inditex Tech Test.postman_collection.json**) to test the endpoint.