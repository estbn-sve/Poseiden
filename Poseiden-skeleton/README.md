# spring-boot

##Application:

## Technical:
1. Framework: Spring Boot v2.0.4
2. Java 8
3. Thymeleaf
4. Bootstrap v.4.3.1

## Install:
You will find below a step by step explanation that tell you how to get a development environment running :

1.Install Java: https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven: https://maven.apache.org/install.html

3.Install postgreSQL : https://www.postgresql.org/download/

# Maven :
## Test :

In a terminal, you go to the source folder of the application and can run the `mvn test` command to perform the tests
## Deploy :

In a terminal, you go to the source folder of the application and can run the `mvn package` command to generate the application jar file

After generate jar file, run the `mvn install` command for push jar file in the local repository

And last run the `mvn deploy` command for deploy application in your server

But you can only use `mvn deploy` command, this command execute previous commands

## Authorisation :

Users with ADMIN role are authorized to access and manage financial entities AND users

Users with USER role are authorized to access and manage financial entities but are NOT authorized to access and manage users

## Authentification :

Authentification session with formLogin

Security and connection to the application
A Security layer is included within the application.

1.Authentication : Every user needs to authenticate to the application with username and password.

2.Authorization : Two roles are available within the application : ADMIN and USER.

3.Password : Users passwords are hashed before being stored in the database.

When the application starts, 2 users are recorded and have access to the application :

A user having a ADMIN role : username = springAdmin / password = secret

A user having a USER role : username = springUser / password = secret

You can use these credentials to connect to the application.

Authentification token with protocol Oauth2

## Endpoints : 
Endpoints are available for managing financial entities with CRUD methods. Endpoints are available for each entity :

Rating, BidList, CurvePoint, RuleName and Trade.
You will find below endpoints for Rating management :

1.Read :
GET http://localhost:8080/rating/list to list all Ratings

2.Create :
GET http://localhost:8080/rating/add to get the form to fill to add a new Rating
POST http://localhost:8080/rating/validate to post the form filled to add a new Rating

3.Update :
GET http://localhost:8080/rating/update/{id} to get the form to fill to update the Rating which id is in parameter
POST http://localhost:8080/rating/update/{id} to post the form filled to update the Rating which id is in parameter

4.Delete :
GET http://localhost:8080/rating/delete/{id} to delete the Rating which id is in parameter

