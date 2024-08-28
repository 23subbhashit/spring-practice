# Explanation of EmpController in Spring Boot

The provided Java code is a controller class (`EmpController`) for a Spring Boot application that manages employee data. It leverages RESTful web services to allow clients to perform CRUD (Create, Read, Update, Delete) operations on `Emp` objects, which represent employees.

## 1. Annotations

- **`@RestController`**: Indicates that this class is a REST controller, meaning it will handle HTTP requests and return data as JSON or XML by default.

- **`@RequestMapping("/api/v1/employees")`**: Specifies the base URL path for all the endpoints in this controller. All endpoints will start with `/api/v1/employees`.

- **`@Autowired`**: Automatically injects an instance of `EmpService` into the `EmpController` class. This is part of Spring's dependency injection mechanism.

## 2. Dependency Injection

- **`private EmpService empService;`**: This is a service layer component that contains the business logic for managing `Emp` entities. The `@Autowired` annotation ensures that Spring injects an instance of `EmpService` into this field.

## 3. CRUD Endpoints

The controller provides five endpoints to manage employee data:

### a. **GET `/api/v1/employees`** - `getAllEmps()`

- **Purpose**: Fetches and returns a list of all employee records.
- **Method**: `GET`
- **Response**: A list of `Emp` objects. The method calls `empService.getAllEmps()` to retrieve the data.

### b. **GET `/api/v1/employees/{id}`** - `getEmpById(@PathVariable Long id)`

- **Purpose**: Fetches a single employee record by its ID.
- **Method**: `GET`
- **Response**: Returns a `ResponseEntity` containing an `Emp` object if found; otherwise, it returns a `404 Not Found` status.
- **Logic**: Uses `Optional<Emp>` to handle the potential absence of an employee with the specified ID. If the employee exists (`emp.isPresent()`), it returns the employee with a `200 OK` status. If not, it returns `404 Not Found`.

### c. **POST `/api/v1/employees`** - `createEmp(@RequestBody Emp emp)`

- **Purpose**: Creates a new employee record.
- **Method**: `POST`
- **Request Body**: Expects an `Emp` object in the request body (`@RequestBody`).
- **Response**: Returns the created `Emp` object after saving it via `empService.createEmp(emp)`.

### d. **PUT `/api/v1/employees/{id}`** - `updateEmp(@PathVariable Long id, @RequestBody Emp empDetails)`

- **Purpose**: Updates an existing employee's details.
- **Method**: `PUT`
- **Request Body**: Expects updated employee details in the request body.
- **Response**: Returns the updated `Emp` object if the update is successful. If the employee is not found, it catches a `RuntimeException` and returns a `404 Not Found` status.
- **Logic**: Calls `empService.updateEmp(id, empDetails)` to attempt the update. If the employee doesn't exist, the service throws a `RuntimeException`, and the controller returns `404 Not Found`.

### e. **DELETE `/api/v1/employees/{id}`** - `deleteEmp(@PathVariable Long id)`

- **Purpose**: Deletes an employee record by ID.
- **Method**: `DELETE`
- **Response**: Returns a `204 No Content` status, indicating successful deletion without returning any body content.
- **Logic**: Calls `empService.deleteEmp(id)` to delete the employee. The service handles any exceptions or checks needed.


# Dependency Injection Explained with Examples

Dependency Injection (DI) is a design pattern that allows an object or class to receive its dependencies from an external source rather than creating them internally. This approach promotes loose coupling and enhances testability and maintainability. Let's explore DI with examples in different contexts.

## 1. Constructor Injection

**Constructor Injection** involves providing dependencies through a class constructor. This method is commonly used because it ensures that the dependencies are immutable after the object is created.

### Advantages

- **Immutability**: Dependencies are set only once through the constructor and cannot be changed later.
- **Required Dependencies**: Ensures that all required dependencies are provided at the time of object creation.

## 2. Setter Injection

**Setter Injection** involves providing dependencies through setter methods. This allows dependencies to be changed or replaced after the object is created.

### Advantages

- **Flexibility**: Dependencies can be changed or updated after the object has been created.
- **Optional Dependencies**: Allows setting dependencies optionally, which can be useful in certain scenarios.

## 3. Field Injection

**Field Injection** involves providing dependencies directly into the fields of a class, typically using annotations in frameworks like Spring. This method is less preferred compared to constructor injection because it makes the dependencies less visible and harder to manage.

### Advantages

- **Convenience**: Simplifies the code by directly injecting dependencies into fields.
- **Reduced Boilerplate**: Eliminates the need for constructor or setter methods for dependency injection.

## Code Examples

### Constructor Injection

```java
public class Engine {
    public void start() {
        System.out.println("Engine started");
    }
}

public class Car {
    private final Engine engine;

    // Constructor injection
    public Car(Engine engine) {
        this.engine = engine;
    }

    public void start() {
        engine.start();
        System.out.println("Car started");
    }
}
public class Main {
    public static void main(String[] args) {
        Engine engine = new Engine(); // Create an instance of Engine
        Car car = new Car(engine); // Inject the Engine dependency into Car
        car.start();
    }
}
