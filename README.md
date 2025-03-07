# Spring-Boot-CRUD-Application

## Table of contents 

- [Project Desciption](#project-description)
- [How to Run This Project](#how-to-run-this-project)
- [Future Plans](#future-plans)


## Project Description

This is a simple Spring Boot CRUD Application. The project consists of three microservices: 

- **Player Microservice** - handles requests related to the ```Player``` entity.

- **Club Microservice** - handles requests related to the ```Club``` entity.

- **Gateway Microservice** - acts as an API gateway for routing requests.

There is a one-to-many relationship between the ```Club``` and the ```Player``` entities. The **Club Microservice** uses an event repository to notify **Player Microservice** about updates. Each microservice - except the **Gateway Microservice** - is connected to a separate ```PostgreSGL``` database. 

In addition to this, GitHub Actions are used to build Docker images for all microservices. These images can be run using a ```docker-compose``` file.

Project Structure:

- .github/workflow - contains GitHub workflow files for building Docker images.

- docker - contains the ```docker-compose``` file for running all microservices and databases.

- spring-boot - contains the implementation of all microservices using the Spring Boot framework.


## How to Run This Project

⚠ **Attention:** You must have Docker installed on your machine! 🐳

1. Clone the repository.

```
git clone https://github.com/arekflis/Spring-Boot-CRUD-Application.git
```

2. Navigate to the ```docker``` folder.

```
cd docker
```

3. Run the project using the ```docker-compose``` file.

```
docker-compose up --build
```

4. Now you can send requests to the **Gateway Microservice** at ```http://localhost:8080```.


## Future Plans 

- Develop a frontend using ```Angular```. 

- Add **Eureka Service Discovery** and **Load Balancer** to the Spring Boot project.

- Test the Spring Boot application using ```jUnit```.

- Test the GUI using ```Selenium``` (in Python or Java).
