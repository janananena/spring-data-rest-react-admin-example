# SpringDataRest-ReactAdmin-Example

a running basic application example having **login/logout** working without **development cors** problems

### backend
created with [spring initializr](https://start.spring.io)

`bootRun` with `SPRING_PROFILES_ACTIVE=dev` to include CORS config and Testdata as well as a testuser `user` with password **user**

* Kotlin
* Spring Boot (3.0.4)
    * spring-boot-starter-web
    * spring-boot-starter-data-jpa
    * spring-boot-starter-data-rest
    * spring-boot-starter-security

### frontend
created with `yarn create vite frontend --template react-ts`

`cd frontend/`
run with `yarn dev`

* Typescript
* React Admin (4.8.3)
    * github:mrmimo/ra-data-json-hal
