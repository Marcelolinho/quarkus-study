# quarkus-study

This is the repo to create a simple User CRUD API just to test out quarkus features.

## Inject

This is the same as @Autowired in Spring. It is used to inject a bean into another bean.

```java
@Inject
UserRepository userRepository;
```

It basically does the IOC (Inversion of Control) for us. We don't have to create an instance of the UserRepository class, quarkus will do it for us.

## RESTEasy Reactive
This is the reactive version of RESTEasy. It is used to create RESTful web services. It is based on the reactive programming model, which is a programming paradigm that allows us to write asynchronous and non-blocking code.

```java@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
```

There we need to tell what it produces and consumes.# quarkus-study
