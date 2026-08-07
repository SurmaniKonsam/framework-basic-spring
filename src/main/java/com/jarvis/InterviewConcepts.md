## What does Spring-Context Dependency do, one of the most important backbone of spring-core?

### Correct Project Structure to place your controller, service, and other beans?
```textmate
src/main/java
│
└── org
    └── example
        ├── Main.java   ← ***@SpringBootApplication*** -> SpringApplication.run(Main.class, args); Main.class, 
        |        tells which is your root package.
        ├── controller
        │      └── HomeController.java
        ├── service
        │      └── UserService.java
        └── repository
               └── UserRepository.java

```

#### But why should Main.java go under ***org.example***, what will happen if Controller is put outside the example folder
### of the example folderWhat is Root Package, why is it so important to know?
```textmate
- First, what is a Root package?
    - The package containing Main.java (annotated with @SpringBootApplication) is called the root package.
    - @SpringBootApplication, reads or scans for all beans required automatically up in the root package.
    - All the other beans defined under the root packages are called the ***sub packages***
    this is where the ***@SpringBootApplication*** cans for beans.
    - @SpringBootApplication scans its own package and every package below it, 
    but never packages outside that hierarchy.
```

### FLow of Spring-Context
```textmate
spring-context
        │
    Provides
        ▼
ApplicationContext
        │
 Loads beans.xml
        │
Reads component-scan
        │
Creates Beans
        │
Stores them in IOC Container
```

### What does spring-context dependency do?
```textmate
What does the spring-context dependency do?

It is one of the core modules of the Spring Framework. It provides the 
- One of the core modules of the Spring Framework.
- Provides the **IOC Container**
- Provides **ApplicationContext** and **ClassPathXmlApplicationContext**
- **Reads beans.xml** (Spring Core) **to create and manage beans.**
- Supports component scanning using <context:component-scan>.
    - What does context:component-scan do?
        - <context:component-scan base-package="com.jarvis"/>
        - This tells Spring:
            "Scan the package com.jarvis and all its sub-packages for classes 
            annotated with 
                - @Component, 
                - @Service, 
                - @Repository, and 
                - @Controller, then register them as beans."
    - When should <context:component-scan> be used over ClassPathXmlApplicationContext?
        - ClassPathXmlApplicationContext
                │
          Loads beans.xml
                │
         beans.xml contains
                ▼
        - Instead of manually defining every bean using <bean>, 
            Spring can automatically discover classes annotated with 
                - @Component, 
                - @Service, 
                - @Repository, and 
                - @Controller using <context:component-scan>. 
            The beans.xml file is still loaded by ClassPathXmlApplicationContext, 
            but now it mainly contains the component-scan configuration rather 
            than numerous <bean> definitions.
            
<context:component-scan>
- Manages the complete bean lifecycle.**
```

#### <context:component-scan> main power?
```xml 
<!--Without <context:component-scan-->
<bean id="userService"
      class="com.jarvis.UserService"/>

<bean id="userRepository"
      class="com.jarvis.UserRepository"/>
```
```java
class UserService{
    public UserService(UserRepository userRepository){}
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"

       xsi:schemaLocation="
       http://www.springframework.org/schema/beans
       https://www.springframework.org/schema/beans/spring-beans.xsd

       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">

    <!-- Automatically scan for @Component, @Service,
         @Repository, @Controller -->

    <context:component-scan base-package="com.jarvis"/>
</beans>

```

### Where should beans.xml must be located
```textmate
src
└── main
    └── resources
         └── beans.xml
```

### IOC Container.
```textmate
In conclusion, the final home where all beans go is in the IOC container.all

spring-context dependency
          │
      provides
          ▼
ClassPathXmlApplicationContext
          │
        loads
          ▼
      beans.xml
          │
Reads XML Configuration
          │
├── <context:component-scan> → Scans packages 
│
├── <bean> → Defines Beans
│
├── <constructor-arg> → Constructor Injection
│
└── <property> → Setter Injection
          │
Creates & Registers Beans
          ▼
IOC Container (Home of all Spring-managed Beans)
```

### Constructor Injection.
#### 1. For Reference injection
Eg.
```java
class UserService {
    public UserService(UserRepository repository) {
    }
}
```
#### Constructor injection.
```xml
<bean id="userRepository"
      class="com.jarvis.UserRepository"/>

<bean id="userService"
      class="com.jarvis.UserService">
    <!--The value is defined under the parent class -->
    <constructor-arg ref="userRepository"/>

</bean>
```

#### 2. For injecting value -> Constructor Injection (Value)

Eg.
```java
class Student {
    public Student(String name, int age) {
    }
}
```

```xml
<bean id="student"
      class="com.jarvis.Student">

    <constructor-arg value="Goku"/>
    <constructor-arg value="25"/>

</bean>
```


### Setter Injection
#### 1. Reference Injection
```Java
class UserService {
    public void setRepository(UserRepository repository) {
    }
}
//User service needs UserRepository
```
```xml
<bean id="userRepository"
      class="com.jarvis.UserRepository"/>

<bean id="userService"
      class="com.jarvis.UserService">

    <property name="repository"
              ref="userRepository"/>

</bean>
```

#### 2. Setter Injection (Value)
```Java
class Student{
    public void setName(String name) { }

    public void setAge(int age) { }
}
```
```xml
<bean id="student"
      class="com.jarvis.Student">

    <property name="name" value="Goku"/>
    <property name="age" value="25"/>

</bean>
```

### Constructor Vs Property

#### Why should Property injection be preferred over construction injection.
```textmate
✅ Optional dependency, means dependency can be mutated later.
✅ Mutable
✅ Flexible
```

#### ### Why should Constructor injection be preferred over Property injection.
```textmate
✅ Dependency is mandatory.
✅ Object is fully initialized when created, constructor initialize the value the moment the object is initialized or when the object is created, 
hence we can use the power of constructor to our advantage.
    - ✅ Constructor injection uses the constructor to initialize mandatory values or dependencies 
    during object creation, ensuring the object is complete from the very beginning.
✅ Can make fields final.
✅ Easy to unit test (pass a mock directly).
✅ Prevents NullPointerException due to missing dependencies.

***FLOW***
Object Creation
      │
Constructor Called
      │
Dependencies Initialized
      │
Object Ready to Use ✅
```


### How does spring boot read your application?
#### 1. You start the application.
```textmate
SpringApplication.run(Main.class, args);

Main.class defines the root package, where all its subpackages will thereafter be scaned
```

#### 2. Spring boot creates IOC container
```textmate
SpringApplication.run()
        │
Creates
        ▼
ApplicationContext (IOC Container)
```

#### 3. Component Scanning
```textmate
@SpringBootApplication
        │
  @ComponentScan
        │
      Finds:
            - @Controller
            - @Service
            - @Repository
            - @Component
        |
  Then out of this scans, beans are created. 
```


#### 4. Configuration File
```textmate
Spring Boot reads -> application.properties, where server port is defined -> server.port = 8080
Tomcat now will run on port 8080
```


#### 5. Complete Flow
```textmate
SpringApplication.run(Main.class, args)
          │
Identifies the Root Package
(package containing @SpringBootApplication)
          │
Creates ApplicationContext
(with the help of spring-context) (IOC Container)
          │
Reads application.properties / application.yml
(to load application configuration such as server.port,
database credentials, logging, profiles, etc.)
          │
Performs Component Scan
(starting from the Root Package and recursively scanning
all its sub-packages)
          │
Finds @Controller, @Service,
@Repository, @Component
          │
Creates & Registers Beans
in the IOC Container
          │
Performs Dependency Injection
          │
Calls @PostConstruct (if present)
          │
Starts Embedded Tomcat
(using configuration loaded from application.properties,
e.g., server.port=8080)
          │
Waits for HTTP Requests
          │
Browser Request
          │
Embedded Tomcat
          │
DispatcherServlet
          │
HandlerMapping
(finds the matching Controller method)
          │
Controller
          │
Business Logic
(Service → Repository → Database)
          │
Returns ResponseEntity / Object
          │
Jackson (HttpMessageConverter)
(converts Java Object ↔ JSON)
          │
HTTP Response
```