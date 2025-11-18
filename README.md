# Moviefy by André Martins

## Core ideas

### Project architecture

For this project I've decided to follow the architecture in the same way that we have learn it during this course, this maintaining maintainability in the system.

The project follows a three layer architecture:

- **Presentation Layer:** Layer allowing to connect the user to the application though JSF (together with the Primefaces library)
- **Business Logic Layer:** Layer related to all business processes such as managing all entities as well as maintaining the API integration
- **Persistence Layer:** Layer representing all entities of the database (Movie, Actor and Director)

### Database definition

Here you can find a list with all the entities that have been defined for this project as well as the associated relationships:

- Movie -> Director: Many-to-One

- Movie -> Actors: Many-to-Many

- **Director:** Contains name and birth year

- **Actor:** Contains personal details and a list of movies

## How to run the application

On the running Wildly server that has been provided to us to develop this assignment (Wildly + Mariadb), start by inserting the dumping the `DB.dump` that has been provided inside your Mariadb container. 
Once the teh database has been started, you can automatically run the application in your server by running the following command `mvn package widfly:deploy`. The `pom.xml` will ensure that all the needed dependencies are installed before deploying the project.   
Once the application is running, you can now access the application via `<your-wildfly-server-access-port>/Exercise1`.

## External links

- **Demo screencast:** ![link](https://youtu.be/6lnZssJtII0)
- **Github repository:** ![link](https://github.com/oliveira-andre-unilu/java_enterprise_HW1.git)