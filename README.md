Dropwizard + Spring Security Sample
===================


This sample demonstrates how to use [Spring Security](http://projects.spring.io/spring-security/) for authenticating requests to REST services implemented with [Dropwizard](http://dropwizard.io/).

The sample uses [Spring Framework](http://projects.spring.io/spring-framework/) for dependency injection and it shows both XML and Java based Spring configuration.

----------


Highlights
-------------

* **Spring Security** for authentication
* **Spring Framework** for dependency injection
* Shows how to get hold of the authenticated user through a Principal object, enabling to run any subsequent authorization logic
* Demonstrates how to take advantage of Dropwizard built-in capability of caching succesfully authenticated credentials
* Makes self evident how to create other variations of authenticators enabling to run path specific authentication logic
* Packaging as a fat jar file, making the app wholly self containted

> **Note:** The sample is configured to use **Basic Authentication**, other authentication schemes supported by Spring Security may be specified in `spring-context.xml`



Build and Run the Application
-------------

To package the application run:

```
mvn package
```

----------

To run the application:

```
java -jar target/dropwizard-spring-security-sample-0.0.1-SNAPSHOT.jar server myapp.yml
```

----------

To make a successfully authenticated request use a tool like [httpie](https://github.com/jakubroztocil/httpie):

```
http -a user@domain.com:supersecret GET http://localhost:8080/hello
```

and the server should politely greet you back.

