# Spring Framework Mastery

Learning Spring Framework from first principles.

## Topics To Be Covered
- Spring Core
- IoC Container
- Dependency Injection
- Bean Lifecycle
- Spring Architecture

## Current Progress
- Basic IoC Container setup
- Bean creation using @Bean
- ApplicationContext usage
- How to push your code from local to remote, without any cloning from the remote repository.

## Spring Mastery Roadmap
spring-mastery 
- spring-core 
- spring-boot 
- spring-mvc 
- spring-data-jpa 
- spring-security 
- microservices 
- advanced-projects


## How to build spring-framework project structure
- Build plain java project using maven in intellij, not maven archetype, we will discuss that later.
- Project structure will be pre-built for you
  - src
    - main
      - java
        - com.jarvis, your artifact id, which you have defined when building the project
          - get your folder structure defined
            - config
              - Where your @Configuration goes
            - service
              - main -> where your spring boot starter code goes
  - pom.xml
    - Where you will define your artifactId, groupId of the project and other required dependencies, 
      - spring-context -> most important dependency which manages your Inversion of controller container.
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
<modelVersion>4.0.0</modelVersion>

    <groupId>com.jarvis</groupId>
    <artifactId>framework-basic-spring</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <!-- Source: https://mvnrepository.com/artifact/org.springframework/spring-context -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>6.2.18</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

</project>
```
## How project structure really looks like
![Alt text](src/main/resources/images/Screenshot 2026-05-13 at 2.04.45 PM.png)
