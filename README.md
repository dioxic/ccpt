# Overview

## Components
* Spring Boot
* ReactJs
* Maven
* H2
* JUnit

# Execution

### Production mode

React source code is bundled and minified. All js dependencies will be downloaded if they do not exist.

Note: unfortunately in this mode the javascript takes over the brower and the swagger ui is not accessible.

```
> mvn spring-boot:run
```

### Dev mode

The step to bundle the react frontend is omitted. Only the backend will start.

```
> mvn clean spring-boot:run -Pdev
```

So long as JS dependencies have been downloaded, the frontend can be loaded from source using yarn (run in prod mode to download JS dependencies)

```
> cd frontend
> yarn start
```

# Bugs / TODO

* delete button doesn't remove deleted cards from the list (it does execute a DELETE on the server side)
* swagger-ui only accessible in dev mode
* no frontend validation
* backend validation errors are not displayed on the frontend
* backend url hardcoded to localhost in JS
* no frontend testing
* server-side unit tests should be mocking components to isolate functionality (i.e. CardController tests should be using mocked repositories)
* no comments