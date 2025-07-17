
# Spring Boot REST API – Quickstart (Udemy Course)

This project follows the structure and lessons from a Udemy course to create a RESTful API using **Spring Boot** with **Gradle**. Below is a guide and summary of key concepts covered.

---

## 📁 RESTful API URI Design

### Example URI

```http
GET /api/users?start=1&limit=50
````

### Simple Endpoints

* `GET /users/1`
* `DELETE /users/1`
* `PUT /users/1`

### Complex/Deep URIs

```http
GET /users/1/messages/5/comments/1
```

### URI Naming Best Practices

* Use **nouns**, not verbs → `/users` ✅ vs `/getUsers` ❌
* Use **plurals** → `/users/1` ✅ vs `/user/1` ❌
* Make them **predictable and simple**

---

## 🌐 HTTP Methods – RESTful vs Non-RESTful

| Action | Non-RESTful                              | RESTful           |
| ------ | ---------------------------------------- | ----------------- |
| Create | POST `/RegisterUserServlet`              | POST `/users`     |
| Read   | GET `/GetUserDetailsServlet?userId=1`    | GET `/users/1`    |
| Update | POST `/UpdateUserDetailsServlet`         | PUT `/users/1`    |
| Delete | GET `/DeleteUserDetailsServlet?userId=1` | DELETE `/users/1` |

---

## 🔧 HTTP Request Handling

### @PathVariable

```java
@GetMapping("/users/{userId}")
public User getUser(@PathVariable String userId)
```

### @RequestParam

```java
@GetMapping("/users")
public String getUsers(@RequestParam int page, @RequestParam int limit)
```

* First query param uses `?`, others use `&`
* Use `required=false` and `defaultValue` for optional parameters

---

## 📤 Returning Data

### Java Object Response

* Create a `UserRest` class with `id`, `firstName`, `email`, etc.
* Return object directly or wrap with `ResponseEntity<>`

```java
return new ResponseEntity<>(user, HttpStatus.OK);
```

### Format as JSON or XML

* Use `produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }`
* Jackson XML dependency is required

---

## 📨 HTTP POST: Create User

### Request Model – `UserDetails`

```java
public class UserDetails {
    @NotNull
    @Size(min = 2)
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String password;
}
```

### Controller Method

```java
@PostMapping(
  consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
  produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
)
public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetails userDetails) {
    ...
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
}
```

---

## ✏️ HTTP PUT: Update User

### Request Model – `UpdateUserDetails`

* Contains only first name and last name.

### Logic

* Use `@PathVariable` for ID, `@RequestBody` for update data
* Fetch existing user from memory (`Map`)
* Update values and return updated user

---

## ❌ HTTP DELETE: Delete User

```java
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    users.remove(id);
    return ResponseEntity.noContent().build();
}
```

---

## 🛠️ Exception Handling

### Global Exception Handler

* Annotate class with `@ControllerAdvice`
* Extend `ResponseEntityExceptionHandler`

```java
@ExceptionHandler(Exception.class)
public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
    ErrorMessage error = new ErrorMessage(new Date(), ex.getMessage());
    return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
}
```

### Custom Exception

```java
public class UserServiceException extends RuntimeException {
    public UserServiceException(String message) {
        super(message);
    }
}
```

Throw it manually in controller:

```java
throw new UserServiceException("Something went wrong");
```

Catch multiple exceptions:

```java
@ExceptionHandler({UserServiceException.class, NullPointerException.class})
```

---

## ♻️ Dependency Injection (DI)

### Why?

* Keeps code modular, testable, and loosely coupled

### Inject Service in Controller

```java
@Autowired
UserService userService;
```

---

## 🧱 Constructor-Based DI – Injecting Utility Class

### Utility Class

```java
@Service
public class Utils {
    public String generateUserId() {
        return UUID.randomUUID().toString();
    }
}
```

### Inject Utility Class into Service

```java
@Service
public class UserServiceImpl implements UserService {

    private final Utils utils;

    @Autowired
    public UserServiceImpl(Utils utils) {
        this.utils = utils;
    }
}
```

---

## 🚀 Standalone JAR – Running Spring Boot App Outside IDE

### Maven Steps

```powershell
cd "C:\Users\username\code folder\udemy\spring first"
mvn install
cd target
java -jar spring-first-0.0.1-SNAPSHOT.jar
```

Optional cleanup:

```powershell
mvn clean
mvn install
```

---

### Gradle Steps

```powershell
cd "C:\Users\username\code folder\udemy\spring first"
./gradlew bootJar
cd build/libs
java -jar spring-first-0.0.1-SNAPSHOT.jar
```

Then test endpoints using Postman.

---

## 📁 Project Structure

```txt
src/
├── main/
│   ├── java/
│   │   └── com/example/springfirst/
│   │       ├── ui/
│   │       │   ├── controller/
│   │       │   └── model/
│   │       │       ├── request/
│   │       │       ├── response/
│   │       │       └── update/
│   │       ├── userservice/
│   │       ├── userservice/impl/
│   │       ├── shared/
│   │       └── exceptions/
│   └── resources/
│       └── application.properties
```