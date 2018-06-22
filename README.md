# Fantasy project

## Overview
Fantasy project is an data store for game locations and characters. 
It is realized as JavaEE application deployable on Wildfly server.

## Modules

#### Deploys

#### fantasy-beans
Core of the project. Contains database connection and models.
This module serves his functionality as EJB remote beans. 

- data persistence
- authorization
- writing to JMS Topic

Example EJB lookup:
```java
private static UserRepository lookupUserRepository() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();

        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");

        final Context ctx = new InitialContext(jndiProperties);

        return (UserRepository) ctx
                .lookup("ejb:/fantasy-beans-1.0-SNAPSHOT/UserRepositoryBean!" + UserRepository.class.getName());
    }
```

#### fantasy-web
Web layer. Provides authentication and REST services.

- authentication
- REST endpoints
- consuming JMS messages

Example REST call:
```http request
GET localhost:8080/categoryDefinitions
Accept: application/json
Authorization: {{token}}
```

#### fantasy-soap
This module contains some of the fantasy-beans functionality as SOAP services.
WSDL is located in `http://localhost:8080/soap/CategoryDefinitionService?wsdl`

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
User can use only one token - cannot have multiple sessions.

## Authorization

User can have two roles: `USER` or `ADMIN`. Every category must belong to exactly one user.
Everyone can get all elements and all categories. Given category and all elements that belongs to it can be modified only by owner or user with `ADMIN` role.
