# Fantasy project

## Overview
Fantasy project is an data store for game locations and characters. 
It is realized as JavaEE application deployable on Wildfly server.

## Modules

#### Deploys

#### fantasy-beans
Core of the project. Contains database connection and models.
This module serves his functionality as EJB remote beans. 
It also provides authorization.

#### fantasy-web
Web layer. Provides authentication and REST services.

#### fantasy-soap
This module contains some of the fantasy-beans functionality as SOAP services.

### Client modules

#### fantasy-se
JavaSE console application for new user registration.

#### py-soap-client
Example SOAP client as Python script.

## Database schema

![alt text](https://image.prntscr.com/image/MBrBAxdgQR6-kIhW2tlwfQ.png)

## Authentication

Users in web layer are authenticated by JWT tokens. It contains `userId` and `role`.
Every request must contains JWT token in `Authorization` header.

